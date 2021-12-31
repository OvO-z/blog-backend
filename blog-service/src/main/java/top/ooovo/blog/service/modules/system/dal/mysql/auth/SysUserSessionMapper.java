package top.ooovo.blog.service.modules.system.dal.mysql.auth;

import org.apache.ibatis.annotations.Mapper;
import top.ooovo.blog.service.modules.system.dal.dataobject.auth.SysUserSessionDO;
import top.ooovo.framework.mybatis.core.mapper.BaseMapperX;

/**
 * @author QAQ
 * @date 2021/12/2
 */

@Mapper
public interface SysUserSessionMapper extends BaseMapperX<SysUserSessionDO> {
}
