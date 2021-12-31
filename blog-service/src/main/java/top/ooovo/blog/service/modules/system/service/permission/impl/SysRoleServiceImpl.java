package top.ooovo.blog.service.modules.system.service.permission.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import top.ooovo.blog.service.modules.system.controller.permission.vo.role.SysRoleCreateReqVO;
import top.ooovo.blog.service.modules.system.controller.permission.vo.role.SysRoleExportReqVO;
import top.ooovo.blog.service.modules.system.controller.permission.vo.role.SysRolePageReqVO;
import top.ooovo.blog.service.modules.system.controller.permission.vo.role.SysRoleUpdateReqVO;
import top.ooovo.blog.service.modules.system.convert.permission.SysRoleConvert;
import top.ooovo.blog.service.modules.system.dal.dataobject.permission.SysRoleDO;
import top.ooovo.blog.service.modules.system.dal.mysql.permission.SysRoleMapper;
import top.ooovo.blog.service.modules.system.enums.SysErrorCodeConstants;
import top.ooovo.blog.service.modules.system.enums.permission.RoleCodeEnum;
import top.ooovo.blog.service.modules.system.enums.permission.SysRoleTypeEnum;
import top.ooovo.blog.service.modules.system.service.permission.SysRoleService;
import top.ooovo.framework.common.enums.CommonStatusEnum;
import top.ooovo.framework.common.exception.util.ServiceExceptionUtil;
import top.ooovo.framework.common.pojo.PageResult;
import top.ooovo.framework.mybatis.core.dataobject.BaseDO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static top.ooovo.blog.service.modules.system.enums.SysErrorCodeConstants.*;

/**
 * 角色 Service 实现类
 * @author QAQ
 * @date 2021/11/29
 */

@Service
@Slf4j
public class SysRoleServiceImpl implements SysRoleService {



    /**
     * 定时执行 {@link #schedulePeriodicRefresh()} 的周期
     *
     */
    private static final long SCHEDULER_PERIOD = 5 * 60 * 1000L;

    /**
     * 角色缓存
     * key：角色编号 {@link SysRoleDO#getId()}
     *
     * 这里声明 volatile 修饰的原因是，每次刷新时，直接修改指向
     */
    private volatile Map<Long, SysRoleDO> roleCache;

   /**
     * 缓存角色的最大更新时间，用于后续的增量轮询，判断是否有更新
     */
    private volatile Date maxUpdateTime;

    @Resource
    private SysRoleMapper roleMapper;

    @Override
    @PostConstruct
    public void initLocalCache() {
        // 获取角色列表，如果有更新
        List<SysRoleDO> roleList = this.loadRoleIfUpdate(maxUpdateTime);
        if (CollUtil.isEmpty(roleList)) {
            return;
        }
        ImmutableMap.Builder<Long, SysRoleDO> builder = ImmutableMap.builder();
        roleList.forEach(sysRoleDO -> builder.put(sysRoleDO.getId(), sysRoleDO));
        roleCache = builder.build();
        maxUpdateTime = roleList.stream().max(Comparator.comparing(BaseDO::getUpdateTime)).get().getUpdateTime();

        log.info("[initLocalCache][初始化 Role 数量为 {}]", roleList.size());
    }

    @Scheduled(fixedDelay = SCHEDULER_PERIOD, initialDelay = SCHEDULER_PERIOD)
    public void schedulePeriodicRefresh() {
        initLocalCache();
    }

    @Override
    public Long createRole(SysRoleCreateReqVO reqVO) {
        // 校验角色
        checkDuplicateRole(reqVO.getName(), reqVO.getCode(), null);
        // 插入数据库
        SysRoleDO role = SysRoleConvert.INSTANCE.convert(reqVO);
        role.setType(SysRoleTypeEnum.CUSTOM.getType());
        role.setStatus(CommonStatusEnum.ENABLE.getStatus());
        roleMapper.insert(role);

        return role.getId();
    }

    @Override
    public void deleteRole(Long id) {
        // TODO @Ovo: 删除角色
    }

    @Override
    public void updateRole(SysRoleUpdateReqVO reqVO) {
        // TODO @QAQ: 更新角色状态
    }

    @Override
    public void updateRoleStatus(Long id, Integer status) {
        // TODO @QAQ: 更新角色信息
    }

    @Override
    public SysRoleDO getRoleFromCache(Long id) {
        return roleCache.get(id);
    }

    @Override
    public List<SysRoleDO> getRoles(Collection<Integer> statuses) {
        return roleMapper.selectListByStatus(statuses);
    }

    @Override
    public List<SysRoleDO> getRolesFromCache(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return roleCache.values().stream().filter(roleDO -> ids.contains(roleDO.getId())).collect(Collectors.toList());
    }

    @Override
    public boolean hasAnyAdmin(Collection<SysRoleDO> roleList) {
        if (CollectionUtil.isEmpty(roleList)) {
            return false;
        }
        return roleList.stream().anyMatch(roleDO -> RoleCodeEnum.ADMIN.getKey().equals(roleDO.getCode()));
    }

    @Override
    public SysRoleDO getRole(Long id) {
        return roleMapper.selectById(id);
    }

    @Override
    public PageResult<SysRoleDO> getRolePage(SysRolePageReqVO reqVO) {
        return roleMapper.selectPage(reqVO);
    }

    @Override
    public List<SysRoleDO> getRoles(SysRoleExportReqVO reqVO) {
        return roleMapper.listRoles(reqVO);
    }


    /**
     * 如果角色发生变化，从数据库中获取最新的全量角色。
     * 如果未发生变化，则返回空
     *
     * @param maxUpdateTime 当前角色的最大更新时间
     * @return 角色列表
     */
    private List<SysRoleDO> loadRoleIfUpdate(Date maxUpdateTime) {
        // 第一步，判断是否要更新。
        // 如果更新时间为空，说明 DB 一定有新数据
        if (maxUpdateTime == null) {
            log.info("[loadRoleIfUpdate][首次加载全量角色]");
        } else { // 判断数据库中是否有更新的角色
            if (!roleMapper.selectExistsByUpdateTimeAfter(maxUpdateTime)) {
                return null;
            }
            log.info("[loadRoleIfUpdate][增量加载全量角色]");
        }
        // 第二步，如果有更新，则从数据库加载所有角色
        return roleMapper.selectList();
    }


    /**
     * 校验角色的唯一字段是否重复
     *
     * 1. 是否存在相同名字的角色
     * 2. 是否存在相同编码的角色
     *
     * @param name 角色名字
     * @param code 角色额编码
     * @param id 角色编号
     */
    public void checkDuplicateRole(String name, String code, Long id) {
        // 1. 该 name 名字被其它角色所使用
        SysRoleDO role = roleMapper.selectByName(name);
        if (role != null && !role.getId().equals(id)) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.ROLE_NAME_DUPLICATE, name);
        }

        // 2. 是否存在相同编码的角色
        if (!StringUtils.hasText(code)) {
            return;
        }
        // 该 code 编码被其它角色所使用
        role = roleMapper.selectByCode(code);
        if (role != null && !role.getId().equals(id)) {
            throw ServiceExceptionUtil.exception(ROLE_CODE_DUPLICATE, code);
        }
    }

    /**
     * 校验角色是否可以被更新
     *
     * @param id 角色编号
     */
    public void checkUpdateRole(Long id) {
        SysRoleDO roleDO = roleMapper.selectById(id);
        if (roleDO == null) {
            throw ServiceExceptionUtil.exception(ROLE_NOT_EXISTS);
        }
        // 内置角色，不允许删除
        if (SysRoleTypeEnum.SYSTEM.getType().equals(roleDO.getType())) {
            throw ServiceExceptionUtil.exception(ROLE_CAN_NOT_UPDATE_SYSTEM_TYPE_ROLE);
        }
    }
}
