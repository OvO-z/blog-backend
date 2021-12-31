package top.ooovo.blog.service.modules.system.service.auth;

import top.ooovo.blog.service.modules.system.controller.auth.vo.SysAuthLoginReqVO;
import top.ooovo.framework.security.core.service.SecurityAuthFrameworkService;

import javax.validation.Valid;

/**
 * 管理后台的认证 Service 接口
 *
 * 提供用户的账号密码登录、token 的校验等认证相关的功能
 * @author QAQ
 * @date 2021/11/25
 */

public interface SysAuthService extends SecurityAuthFrameworkService {

    /**
     * 账号登录
     *
     * @param reqVO 登录信息
     * @param userIp 用户 IP
     * @param userAgent 用户 UA
     * @return 身份令牌，使用 JWT 方式
     */
    String login(@Valid SysAuthLoginReqVO reqVO, String userIp, String userAgent);
}
