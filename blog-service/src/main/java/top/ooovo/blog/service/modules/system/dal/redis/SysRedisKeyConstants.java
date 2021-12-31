package top.ooovo.blog.service.modules.system.dal.redis;

import top.ooovo.framework.redis.core.RedisKeyDefine;
import top.ooovo.framework.security.core.LoginUser;

import static top.ooovo.framework.redis.core.RedisKeyDefine.KeyTypeEnum.STRING;

/**
 * System Redis Key 枚举类
 * @author QAQ
 * @date 2021/12/1
 */

public interface SysRedisKeyConstants {
    RedisKeyDefine LOGIN_USER = new RedisKeyDefine("登录用户的缓存",
            // 参数为 sessionId
            "login_user:%s",
            STRING, LoginUser.class, RedisKeyDefine.TimeoutTypeEnum.DYNAMIC);
}
