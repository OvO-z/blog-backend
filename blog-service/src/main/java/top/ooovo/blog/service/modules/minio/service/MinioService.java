package top.ooovo.blog.service.modules.minio.service;

import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;
import top.ooovo.blog.service.modules.minio.enums.MinioBucketType;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Minio 对象存储 Service 接口
 * @author ovo
 * @date 2022/07/18
 */

public interface MinioService {

    public String upload(MultipartFile file, MinioBucketType type, Boolean cover);
}
