package top.ooovo.blog.service.modules.system.controller.permission.vo.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author QAQ
 * @date 2021/12/3
 */

@ApiModel("菜单列表 Request VO")
@Data
public class SysMenuListReqVO {

    @ApiModelProperty(value = "菜单名称", example = "首页", notes = "模糊匹配")
    private String name;

    @ApiModelProperty(value = "展示状态", example = "1", notes = "参见 SysCommonStatusEnum 枚举类")
    private Integer status;

}