package top.ooovo.blog.service.modules.blog.controller.category;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.ooovo.blog.service.modules.blog.controller.category.vo.CategoryCreateReqVO;
import top.ooovo.blog.service.modules.blog.controller.category.vo.CategoryPageReqVO;
import top.ooovo.blog.service.modules.blog.controller.category.vo.CategoryRespVO;
import top.ooovo.blog.service.modules.blog.controller.category.vo.CategoryUpdateReqVO;
import top.ooovo.blog.service.modules.blog.convert.category.CategoryConvert;
import top.ooovo.blog.service.modules.blog.service.category.CategoryService;
import top.ooovo.blog.service.modules.system.controller.user.vo.SysUserCreateReqVO;
import top.ooovo.blog.service.modules.system.controller.user.vo.SysUserPageReqVO;
import top.ooovo.blog.service.modules.system.controller.user.vo.SysUserRespVO;
import top.ooovo.blog.service.modules.system.controller.user.vo.SysUserUpdateReqVO;
import top.ooovo.blog.service.modules.system.convert.user.SysUserConvert;
import top.ooovo.framework.common.pojo.CommonResult;
import top.ooovo.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import javax.validation.Valid;

import java.util.List;

import static top.ooovo.framework.common.pojo.CommonResult.success;

/**
 * @author QAQ
 * @date 2022/5/7
 */
@Api(tags = "文章分类")
@RestController
@RequestMapping("/blog/category")
@Validated
@Slf4j
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @PostMapping("/create")
    @ApiOperation("新增分类")
    @PreAuthorize("@ss.hasPermission('blog:category:create')")
    public CommonResult<Long> createCategory(@Valid @RequestBody CategoryCreateReqVO reqVO) {
        Long id = categoryService.createCategory(reqVO);
        return success(id);
    }

    @PutMapping("/update")
    @ApiOperation("修改分类")
    @PreAuthorize("@ss.hasPermission('blog:category:update')")
    public CommonResult<Boolean> updateCategory(@Valid @RequestBody CategoryUpdateReqVO reqVO) {
        categoryService.updateCategory(reqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("批量删除分类")
    @ApiImplicitParam(name = "ids", value = "编号", required = true, example = "1024", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('blog:category:delete')")
    public CommonResult<Boolean> deleteCategory(@RequestBody List<Long> ids) {
        categoryService.deleteCategory(ids);
        return success(true);
    }

    @GetMapping("/page")
    @ApiOperation("获得分类分页列表")
    @PreAuthorize("@ss.hasPermission('blog:category:page')")
    public CommonResult<PageResult<CategoryRespVO>> pageCategories(@Valid CategoryPageReqVO reqVO) {
        return success(CategoryConvert.INSTANCE.convertPage(categoryService.pageCategories(reqVO)));
    }

    @GetMapping("/list")
    @ApiOperation("获得分类分页列表")
    @PreAuthorize("@ss.hasPermission('blog:category:list')")
    public CommonResult<List<CategoryRespVO>> listCategories() {
        return success(CategoryConvert.INSTANCE.convertList(categoryService.listCategories()));
    }
}
