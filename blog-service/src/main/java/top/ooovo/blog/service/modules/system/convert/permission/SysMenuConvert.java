package top.ooovo.blog.service.modules.system.convert.permission;

import top.ooovo.blog.service.modules.system.controller.permission.vo.menu.SysMenuCreateReqVO;
import top.ooovo.blog.service.modules.system.controller.permission.vo.menu.SysMenuRespVO;
import top.ooovo.blog.service.modules.system.controller.permission.vo.menu.SysMenuSimpleRespVO;
import top.ooovo.blog.service.modules.system.controller.permission.vo.menu.SysMenuUpdateReqVO;
import top.ooovo.blog.service.modules.system.dal.dataobject.permission.SysMenuDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author QAQ
 * @date 2021/12/2
 */

@Mapper
public interface SysMenuConvert {
    SysMenuConvert INSTANCE = Mappers.getMapper(SysMenuConvert.class);

    List<SysMenuRespVO> convertList(List<SysMenuDO> list);

    SysMenuDO convert(SysMenuCreateReqVO bean);

    SysMenuDO convert(SysMenuUpdateReqVO bean);

    SysMenuRespVO convert(SysMenuDO bean);

    List<SysMenuSimpleRespVO> convertList02(List<SysMenuDO> list);

}
