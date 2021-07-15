package com.voc.system.dao;

import com.voc.restful.core.persist.mongo.IMongoDao;
import com.voc.system.entity.Relation;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/12 17:33
 */
@NoRepositoryBean
public interface IRelationDao extends IMongoDao<Relation, String> {

    /**
     * 添加用户、角色关系
     *
     * @param userId 用户ID
     * @param roleId 角色ID
     */
    void addUserRoleRelation(String userId, String roleId);

    /**
     * 移除用户、角色关系
     *
     * @param userId 用户ID
     * @param roleId 角色ID
     */
    void removeUserRoleRelation(String userId, String roleId);

    /**
     * 添加用户、菜单关系
     *
     * @param userId 用户ID
     * @param menuId 菜单ID
     */
    void addUserMenuRelation(String userId, String menuId);

    /**
     * 移除用户、菜单关系
     *
     * @param userId 用户ID
     * @param menuId 菜单ID
     */
    void removeUserMenuRelation(String userId, String menuId);

    /**
     * 添加用户、权限关系
     *
     * @param userId      用户ID
     * @param authorityId 权限ID
     */
    void addUserAuthorityRelation(String userId, String authorityId);

    /**
     * 移除用户、权限关系
     *
     * @param userId      用户ID
     * @param authorityId 权限ID
     */
    void removeUserAuthorityRelation(String userId, String authorityId);

    /**
     * 添加角色、菜单关系
     *
     * @param roleId 角色ID
     * @param menuId 菜单ID
     */
    void addRoleMenuRelation(String roleId, String menuId);

    /**
     * 移除角色、菜单关系
     *
     * @param roleId 角色ID
     * @param menuId 菜单ID
     */
    void removeRoleMenuRelation(String roleId, String menuId);

    /**
     * 添加角色、权限关系
     *
     * @param roleId      角色ID
     * @param authorityId 权限ID
     */
    void addRoleAuthorityIdRelation(String roleId, String authorityId);

    /**
     * 移除角色、权限关系
     *
     * @param roleId      角色ID
     * @param authorityId 权限ID
     */
    void removeRoleAuthorityIdRelation(String roleId, String authorityId);

    /**
     * 获取与用户关联的菜单集合
     *
     * @param userId 用户ID
     * @return List<String>
     */
    List<String> getUserMenu(String userId);

    /**
     * 获取与用户关联的权限集合
     *
     * @param userId 用户ID
     * @return List<String>
     */
    List<String> getUserAuthority(String userId);

    /**
     * 获取与用户关联的角色集合
     *
     * @param userId 用户ID
     * @return List<String>
     */
    List<String> getUserRole(String userId);

    /**
     * 获取与角色关联的菜单集合
     *
     * @param roleId 角色ID
     * @return List<String>
     */
    List<String> getRoleMenu(String... roleId);

    /**
     * 获取与角色关联的权限集合
     *
     * @param roleId 角色ID
     * @return List<String>
     */
    List<String> getRoleAuthority(String... roleId);

    /**
     * 获取用户拥有的菜单集合
     *
     * @param userId 用户ID
     * @return Set<String>
     */
    List<String> getUserMenuIds(String userId);

    /**
     * 获取用户拥有的权限集合
     *
     * @param userId 用户ID
     * @return Set<String>
     */
    List<String> getUserAuthorityIds(String userId);

}
