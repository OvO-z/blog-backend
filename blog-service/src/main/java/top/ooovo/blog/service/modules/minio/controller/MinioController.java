package top.ooovo.blog.service.modules.minio.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.ooovo.blog.service.modules.minio.enums.MinioBucketType;
import top.ooovo.blog.service.modules.minio.service.MinioService;
import top.ooovo.framework.common.pojo.CommonResult;

import javax.annotation.Resource;

/**
 * @author ovo
 * @time 2022/07/17
 */

@Api(tags = "Minio对象存储管理")
@RestController
@RequestMapping("/minio")
@Validated
@Slf4j
public class MinioController {

    @Resource
    MinioService service;


    @ApiOperation("上传文件")
    @PostMapping("/upload")
    public CommonResult<String> upload(@RequestParam("file") MultipartFile file) {
         return CommonResult.success(service.upload(file, MinioBucketType.PHOTO, true));
    }
}
