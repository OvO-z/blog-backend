package top.ooovo.blog.service.modules.system.service.dict.impl;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import top.ooovo.blog.service.modules.system.controller.dict.vo.data.DictDataCreateReqVO;
import top.ooovo.blog.service.modules.system.controller.dict.vo.data.DictDataPageReqVO;
import top.ooovo.blog.service.modules.system.controller.dict.vo.data.DictDataUpdateReqVO;
import top.ooovo.blog.service.modules.system.convert.dict.DictDataConvert;
import top.ooovo.blog.service.modules.system.dal.dataobject.dict.DictDataDO;
import top.ooovo.blog.service.modules.system.dal.dataobject.dict.DictTypeDO;
import top.ooovo.blog.service.modules.system.dal.mysql.dict.DictDataMapper;
import top.ooovo.blog.service.modules.system.service.dict.DictDataService;
import top.ooovo.blog.service.modules.system.service.dict.DictTypeService;
import top.ooovo.blog.service.modules.system.service.dict.dto.DictDataRespDTO;
import top.ooovo.framework.common.enums.CommonStatusEnum;
import top.ooovo.framework.common.pojo.PageResult;
import top.ooovo.framework.common.util.collection.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static top.ooovo.blog.service.modules.system.enums.SysErrorCodeConstants.*;
import static top.ooovo.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * @author QAQ
 * @date 2022/6/12
 */

@Service
@Slf4j
public class DictDataServiceImpl implements DictDataService {

    /**
     * 排序 dictType > sort
     */
    private static final Comparator<DictDataDO> COMPARATOR_TYPE_AND_SORT = Comparator
            .comparing(DictDataDO::getDictType)
            .thenComparingInt(DictDataDO::getSort);

    /**
     * 定时执行 {@link #schedulePeriodicRefresh()} 的周期
     * 因为已经通过 Redis Pub/Sub 机制，所以频率不需要高
     */
    private static final long SCHEDULER_PERIOD = 5 * 60 * 1000L;

    /**
     * 字典数据缓存，第二个 key 使用 label
     *
     * key1：字典类型 dictType
     * key2：字典标签 label
     */
    private ImmutableTable<String, String, DictDataDO> labelDictDataCache;
    /**
     * 字典数据缓存，第二个 key 使用 value
     *
     * key1：字典类型 dictType
     * key2：字典值 value
     */
    private ImmutableTable<String, String, DictDataDO> valueDictDataCache;

    /**
     * 缓存字典数据的最大更新时间，用于后续的增量轮询，判断是否有更新
     */
    private volatile Date maxUpdateTime;

    @Resource
    private DictTypeService dictTypeService;

    @Resource
    private DictDataMapper dictDataMapper;

    @Override
    public DictDataRespDTO getDictDataFromCache(String type, String value) {
        return DictDataConvert.INSTANCE.convert02(valueDictDataCache.get(type, value));
    }

    @Override
    public DictDataRespDTO parseDictDataFromCache(String type, String label) {
        return DictDataConvert.INSTANCE.convert02(labelDictDataCache.get(type, label));
    }

    @Override
    public List<DictDataRespDTO> listDictDatasFromCache(String type) {
        return DictDataConvert.INSTANCE.convertList03(labelDictDataCache.row(type).values());
    }

    @Override
    @PostConstruct
    public void initLocalCache() {
        // 获取字典数据列表，如果有更新
        List<DictDataDO> dataList = loadDictDataIfUpdate(maxUpdateTime);
        if (CollUtil.isEmpty(dataList)) {
            return;
        }

        // 构建缓存
        ImmutableTable.Builder<String, String, DictDataDO> labelDictDataBuilder = ImmutableTable.builder();
        ImmutableTable.Builder<String, String, DictDataDO> valueDictDataBuilder = ImmutableTable.builder();
        dataList.forEach(dictData -> {
            labelDictDataBuilder.put(dictData.getDictType(), dictData.getLabel(), dictData);
            valueDictDataBuilder.put(dictData.getDictType(), dictData.getValue(), dictData);
        });
        labelDictDataCache = labelDictDataBuilder.build();
        valueDictDataCache = valueDictDataBuilder.build();
        maxUpdateTime = CollectionUtils.getMaxValue(dataList, DictDataDO::getUpdateTime);
        log.info("[initLocalCache][缓存字典数据，数量为:{}]", dataList.size());
    }

    /**
     * 如果字典数据发生变化，从数据库中获取最新的全量字典数据。
     * 如果未发生变化，则返回空
     *
     * @param maxUpdateTime 当前字典数据的最大更新时间
     * @return 字典数据列表
     */
    private List<DictDataDO> loadDictDataIfUpdate(Date maxUpdateTime) {
        // 第一步，判断是否要更新。
        if (maxUpdateTime == null) { // 如果更新时间为空，说明 DB 一定有新数据
            log.info("[loadDictDataIfUpdate][首次加载全量字典数据]");
        } else { // 判断数据库中是否有更新的字典数据
            if (dictDataMapper.selectCountByUpdateTimeGt(maxUpdateTime) == 0) {
                return null;
            }
            log.info("[loadDictDataIfUpdate][增量加载全量字典数据]");
        }
        // 第二步，如果有更新，则从数据库加载所有字典数据
        return dictDataMapper.selectList();
    }

    @Scheduled(fixedDelay = SCHEDULER_PERIOD, initialDelay = SCHEDULER_PERIOD)
    public void schedulePeriodicRefresh() {
        initLocalCache();
    }


    @Override
    public Long createDictData(DictDataCreateReqVO reqVO) {
        // 校验正确性
        checkCreateOrUpdate(null, reqVO.getValue(), reqVO.getDictType());

        // 插入字典类型
        DictDataDO dictData = DictDataConvert.INSTANCE.convert(reqVO);
        dictDataMapper.insert(dictData);

        return dictData.getId();
    }

    @Override
    public void updateDictData(DictDataUpdateReqVO reqVO) {
        // 校验正确性
        checkCreateOrUpdate(reqVO.getId(), reqVO.getValue(), reqVO.getDictType());

        // 更新字典类型
        DictDataDO updateObj = DictDataConvert.INSTANCE.convert(reqVO);
        dictDataMapper.updateById(updateObj);
    }

    @Override
    public void deleteDictData(Long id) {
        // 校验是否存在
        checkDictDataExists(id);

        // 删除字典数据
        dictDataMapper.deleteById(id);
    }

    @Override
    public PageResult<DictDataDO> getDictDataPage(DictDataPageReqVO reqVO) {
        return dictDataMapper.selectPage(reqVO);
    }

    @Override
    public DictDataDO getDictData(Long id) {
        return dictDataMapper.selectById(id);
    }

    @Override
    public long countByDictType(String dictType) {
        return dictDataMapper.selectCountByDictType(dictType);
    }


    private void checkCreateOrUpdate(Long id, String value, String dictType) {
        // 校验自己存在
        checkDictDataExists(id);
        // 校验字典类型有效
        checkDictTypeValid(dictType);
        // 校验字典数据的值的唯一性
        checkDictDataValueUnique(id, dictType, value);
    }

    private void checkDictDataValueUnique(Long id, String dictType, String value) {
        DictDataDO dictData = dictDataMapper.selectByDictTypeAndValue(dictType, value);
        if (dictData == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的字典数据
        if (id == null) {
            throw exception(DICT_DATA_VALUE_DUPLICATE);
        }
        if (!dictData.getId().equals(id)) {
            throw exception(DICT_DATA_VALUE_DUPLICATE);
        }
    }

    private void checkDictDataExists(Long id) {
        if (id == null) {
            return;
        }
        DictDataDO dictData = dictDataMapper.selectById(id);
        if (dictData == null) {
            throw exception(DICT_DATA_NOT_EXISTS);
        }
    }

    private void checkDictTypeValid(String type) {
        DictTypeDO dictType = dictTypeService.getDictType(type);
        if (dictType == null) {
            throw exception(DICT_TYPE_NOT_EXISTS);
        }
        if (!CommonStatusEnum.ENABLE.getStatus().equals(dictType.getStatus())) {
            throw exception(DICT_TYPE_NOT_ENABLE);
        }
    }


    @Override
    public void validDictDatas(String dictType, Collection<String> values) {
        if (CollUtil.isEmpty(values)) {
            return;
        }
        ImmutableMap<String, DictDataDO> dictDataMap = valueDictDataCache.row(dictType);
        // 校验
        values.forEach(value -> {
            DictDataDO dictData = dictDataMap.get(value);
            if (dictData == null) {
                throw exception(DICT_DATA_NOT_EXISTS);
            }
            if (!CommonStatusEnum.ENABLE.getStatus().equals(dictData.getStatus())) {
                throw exception(DICT_DATA_NOT_ENABLE, dictData.getLabel());
            }
        });
    }
}
