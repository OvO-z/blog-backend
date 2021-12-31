package top.ooovo.blog.service.modules.system.convert.permission;

import top.ooovo.blog.service.modules.system.controller.permission.vo.role.SysRoleCreateReqVO;
import top.ooovo.blog.service.modules.system.controller.permission.vo.role.SysRoleRespVO;
import top.ooovo.blog.service.modules.system.controller.permission.vo.role.SysRoleSimpleRespVO;
import top.ooovo.blog.service.modules.system.controller.permission.vo.role.SysRoleUpdateReqVO;
import top.ooovo.blog.service.modules.system.dal.dataobject.permission.SysRoleDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author QAQ
 * @date 2021/11/29
 */

@Mapper
public interface SysRoleConvert {
    SysRoleConvert INSTANCE = Mappers.getMapper(SysRoleConvert.class);

    SysRoleDO convert(SysRoleUpdateReqVO bean);

    SysRoleRespVO convert(SysRoleDO bean);

    SysRoleDO convert(SysRoleCreateReqVO bean);

    List<SysRoleSimpleRespVO> convertList02(List<SysRoleDO> list);

}
