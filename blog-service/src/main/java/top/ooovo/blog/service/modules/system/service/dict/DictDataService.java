package top.ooovo.blog.service.modules.system.service.dict;

import top.ooovo.blog.service.modules.system.controller.dict.vo.data.DictDataCreateReqVO;
import top.ooovo.blog.service.modules.system.controller.dict.vo.data.DictDataPageReqVO;
import top.ooovo.blog.service.modules.system.controller.dict.vo.data.DictDataUpdateReqVO;
import top.ooovo.blog.service.modules.system.dal.dataobject.dict.DictDataDO;
import top.ooovo.blog.service.modules.system.service.dict.dto.DictDataRespDTO;
import top.ooovo.framework.common.pojo.PageResult;

import java.util.Collection;
import java.util.List;

/**
 * @author QAQ
 * @date 2022/6/12
 */

public interface DictDataService {

    /**
     * 获得指定的字典数据，从缓存中
     *
     * @param type 字典类型
     * @param value 字典数据值
     * @return 字典数据
     */
    DictDataRespDTO getDictDataFromCache(String type, String value);

    /**
     * 解析获得指定的字典数据，从缓存中
     *
     * @param type 字典类型
     * @param label 字典数据标签
     * @return 字典数据
     */
    DictDataRespDTO parseDictDataFromCache(String type, String label);

    /**
     * 获得指定类型的字典数据，从缓存中
     *
     * @param type 字典类型
     * @return 字典数据列表
     */
    List<DictDataRespDTO> listDictDatasFromCache(String type);



    /**
     * 初始化字典数据的本地缓存
     */
    void initLocalCache();

    /**
     * 创建字典数据
     *
     * @param reqVO 字典数据信息
     * @return 字典数据编号
     */
    Long createDictData(DictDataCreateReqVO reqVO);

    /**
     * 更新字典数据
     *
     * @param reqVO 字典数据信息
     */
    void updateDictData(DictDataUpdateReqVO reqVO);

    /**
     * 删除字典数据
     *
     * @param id 字典数据编号
     */
    void deleteDictData(Long id);

    /**
     * 获得字典数据分页列表
     *
     * @param reqVO 分页请求
     * @return 字典数据分页列表
     */
    PageResult<DictDataDO> getDictDataPage(DictDataPageReqVO reqVO);

    /**
     * 获得字典数据详情
     *
     * @param id 字典数据编号
     * @return 字典数据
     */
    DictDataDO getDictData(Long id);

    /**
     * 获得字典数据列表
     *
     * @return 字典数据全列表
     */
    List<DictDataDO> getDictDatas();

    /**
     * 获得指定字典类型的数据数量
     *
     * @param dictType 字典类型
     * @return 数据数量
     */
    long countByDictType(String dictType);

    /**
     * 校验字典数据们是否有效。如下情况，视为无效：
     * 1. 字典数据不存在
     * 2. 字典数据被禁用
     *
     * @param dictType 字典类型
     * @param values 字典数据值的数组
     */
    void validDictDatas(String dictType, Collection<String> values);

}
