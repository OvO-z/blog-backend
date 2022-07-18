package top.ooovo.blog.service.modules.minio.service.impl;

import cn.hutool.core.lang.UUID;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.ooovo.blog.service.modules.minio.enums.MinioBucketType;
import top.ooovo.blog.service.modules.minio.service.MinioService;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author ovo
 * @date 2022/07/18
 */

@Service
public class MinioServiceImpl implements MinioService {

    @Resource
    private MinioClient client;


    @Override
    public String upload(MultipartFile file, MinioBucketType type, Boolean cover) {
        String fileName = file.getOriginalFilename();

        if (!cover) {
            fileName = UUID.fastUUID() + "-" + fileName.substring(fileName.lastIndexOf("."));
        }

        String objectName = type.getApi() + fileName;
        try {
            client.putObject(PutObjectArgs.builder()
                            .object(objectName)
                            .bucket(type.getName())
                            .contentType(file.getContentType())
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .build());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return objectName;
    }
}
