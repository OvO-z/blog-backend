package top.ooovo.blog.service.modules.system.dal.redis.auth;

import top.ooovo.framework.redis.core.service.RedisService;
import top.ooovo.framework.security.config.SecurityProperties;
import top.ooovo.framework.security.core.LoginUser;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import static top.ooovo.blog.service.modules.system.dal.redis.SysRedisKeyConstants.LOGIN_USER;

/**
 * {@link LoginUser} 的 RedisDAO
 * @author QAQ
 * @date 2021/12/1
 */

@Repository
public class SysLoginUserRedisDAO {

    @Resource
    private RedisService redisService;

    @Resource
    private SecurityProperties securityProperties;

    public LoginUser get(String sessionId) {
        String redisKey = formatKey(sessionId);
        return (LoginUser) redisService.get(redisKey);
    }

    public void set(String sessionId, LoginUser loginUser) {
        String redisKey = formatKey(sessionId);
        redisService.set(redisKey, loginUser, securityProperties.getSessionTimeout());
    }

    public void delete(String sessionId) {
        String redisKey = formatKey(sessionId);
        redisService.del(redisKey);
    }


    private static String formatKey(String sessionId) {
        return String.format(LOGIN_USER.getKeyTemplate(), sessionId);
    }
}
