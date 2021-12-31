package top.ooovo.blog.service.modules.system.convert.user;

import top.ooovo.blog.service.modules.system.controller.user.vo.SysUserCreateReqVO;
import top.ooovo.blog.service.modules.system.controller.user.vo.SysUserRespVO;
import top.ooovo.blog.service.modules.system.controller.user.vo.SysUserUpdateReqVO;
import top.ooovo.blog.service.modules.system.dal.dataobject.user.SysUserDO;
import top.ooovo.framework.common.pojo.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author QAQ
 * @date 2021/11/23
 */

@Mapper
public interface SysUserConvert {
    SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);

    SysUserDO convert(SysUserCreateReqVO bean);

    SysUserDO convert(SysUserUpdateReqVO bean);

    SysUserRespVO convert(SysUserDO bean);

    PageResult<SysUserRespVO> convertPage(PageResult<SysUserDO> page);
}
