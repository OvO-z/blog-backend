package top.ooovo.blog.service.modules.system.dal.mysql.dict;

import org.apache.ibatis.annotations.Mapper;
import top.ooovo.blog.service.modules.system.controller.dict.vo.type.DictTypePageReqVO;
import top.ooovo.blog.service.modules.system.dal.dataobject.dict.DictTypeDO;
import top.ooovo.framework.common.pojo.PageResult;
import top.ooovo.framework.mybatis.core.mapper.BaseMapperX;
import top.ooovo.framework.mybatis.core.query.LambdaQueryWrapperX;

import java.util.List;

/**
 * @author QAQ
 * @date 2022/6/12
 */
@Mapper
public interface DictTypeMapper extends BaseMapperX<DictTypeDO> {
    default PageResult<DictTypeDO> selectPage(DictTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DictTypeDO>()
                .likeIfPresent(DictTypeDO::getName, reqVO.getName())
                .likeIfPresent(DictTypeDO::getType, reqVO.getType())
                .eqIfPresent(DictTypeDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(DictTypeDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(DictTypeDO::getId));
    }

    default DictTypeDO selectByType(String type) {
        return selectOne(DictTypeDO::getType, type);
    }

    default DictTypeDO selectByName(String name) {
        return selectOne(DictTypeDO::getName, name);
    }
}
