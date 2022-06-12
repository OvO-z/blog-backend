package top.ooovo.blog.service.modules.system.dal.mysql.dict;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.ooovo.blog.service.modules.system.controller.dict.vo.data.DictDataPageReqVO;
import top.ooovo.blog.service.modules.system.dal.dataobject.dict.DictDataDO;
import top.ooovo.framework.common.pojo.PageResult;
import top.ooovo.framework.mybatis.core.mapper.BaseMapperX;
import top.ooovo.framework.mybatis.core.query.LambdaQueryWrapperX;

import java.util.Arrays;
import java.util.Date;

/**
 * @author QAQ
 * @date 2022/6/12
 */

@Mapper
public interface DictDataMapper extends BaseMapperX<DictDataDO> {

    default DictDataDO selectByDictTypeAndValue(String dictType, String value) {
        return selectOne(new LambdaQueryWrapperX<DictDataDO>().eq(DictDataDO::getDictType, dictType)
                .eq(DictDataDO::getValue, value));
    }


    default long selectCountByDictType(String dictType) {
        return selectCount(DictDataDO::getDictType, dictType);
    }


    default PageResult<DictDataDO> selectPage(DictDataPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DictDataDO>()
                .likeIfPresent(DictDataDO::getLabel, reqVO.getLabel())
                .likeIfPresent(DictDataDO::getDictType, reqVO.getDictType())
                .eqIfPresent(DictDataDO::getStatus, reqVO.getStatus())
                .orderByDesc(Arrays.asList(DictDataDO::getDictType, DictDataDO::getSort)));
    }

    @Select("SELECT COUNT(*) FROM system_dict_data WHERE update_time > #{maxUpdateTime}")
    Long selectCountByUpdateTimeGt(Date maxUpdateTime);


}
