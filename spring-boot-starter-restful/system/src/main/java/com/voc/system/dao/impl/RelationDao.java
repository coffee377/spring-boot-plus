package com.voc.system.dao.impl;

import com.voc.restful.core.persist.mongo.impl.BaseMongoDao;
import com.voc.restful.core.response.BizException;
import com.voc.restful.core.response.impl.BaseBizStatus;
import com.voc.system.dao.IRelationDao;
import com.voc.system.entity.Relation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/12 17:39
 */
@Slf4j
@Repository
public class RelationDao extends BaseMongoDao<Relation, String> implements IRelationDao {

    @Override
    public void addUserRoleRelation(String userId, String roleId) {
        add(Relation.Type.USER_ROLE, userId, roleId, "用户角色关系已存在");
    }

    @Override
    public void removeUserRoleRelation(String userId, String roleId) {
        remove(Relation.Type.USER_ROLE, userId, roleId);
    }

    @Override
    public void addUserMenuRelation(String userId, String menuId) {
        add(Relation.Type.USER_MENU, userId, menuId, "用户菜单关系已存在");
    }

    @Override
    public void removeUserMenuRelation(String userId, String menuId) {
        remove(Relation.Type.USER_MENU, userId, menuId);
    }

    @Override
    public void addUserAuthorityRelation(String userId, String authorityId) {
        add(Relation.Type.USER_AUTHORITY, userId, authorityId, "用户权限关系已存在");
    }

    @Override
    public void removeUserAuthorityRelation(String userId, String authorityId) {
        remove(Relation.Type.USER_AUTHORITY, userId, authorityId);
    }

    @Override
    public void addRoleMenuRelation(String roleId, String menuId) {
        add(Relation.Type.ROLE_MENU, roleId, menuId, "角色菜单关系已存在");
    }

    @Override
    public void removeRoleMenuRelation(String roleId, String menuId) {
        remove(Relation.Type.ROLE_MENU, roleId, menuId);
    }

    @Override
    public void addRoleAuthorityIdRelation(String roleId, String authorityId) {
        add(Relation.Type.ROLE_AUTHORITY, roleId, authorityId, "角色权限关系已存在");
    }

    @Override
    public void removeRoleAuthorityIdRelation(String roleId, String authorityId) {
        remove(Relation.Type.ROLE_AUTHORITY, roleId, authorityId);
    }

    @Override
    public List<String> getUserMenu(String userId) {
        return getUserRelation(userId, Relation.Type.USER_MENU);
    }

    @Override
    public List<String> getUserAuthority(String userId) {
        return getUserRelation(userId, Relation.Type.USER_AUTHORITY);
    }

    @Override
    public List<String> getUserRole(String userId) {
        return getUserRelation(userId, Relation.Type.USER_ROLE);
    }

    @Override
    public List<String> getRoleMenu(String... roleId) {
        return getRoleRelation(Relation.Type.ROLE_MENU, roleId);
    }

    @Override
    public List<String> getRoleAuthority(String... roleId) {
        return getRoleRelation(Relation.Type.ROLE_AUTHORITY, roleId);
    }

    @Override
    public List<String> getUserMenuIds(String userId) {
        /* 1.获取用户菜单 */
        List<String> userMenu = this.getUserMenu(userId);

        /* 2.获取用户角色 */
        List<String> userRole = this.getUserRole(userId);

        /* 3.获取角色菜单 */
        List<String> roleMenu = this.getRoleMenu(userRole.toArray(new String[]{}));

        /* 4. 合并菜单 */
        LinkedHashSet<String> result = new LinkedHashSet<>(userMenu);
        result.addAll(roleMenu);

        return result.stream().sorted(String::compareTo).collect(Collectors.toList());
    }

    @Override
    public List<String> getUserAuthorityIds(String userId) {
        /* 1.获取用户权限 */
        List<String> userAuthority = this.getUserAuthority(userId);

        /* 2.获取用户角色 */
        List<String> userRole = this.getUserRole(userId);

        /* 3.获取角色权限 */
        List<String> roleAuthority = this.getRoleAuthority(userRole.toArray(new String[]{}));

        /* 4. 合并菜单 */
        LinkedHashSet<String> result = new LinkedHashSet<>(userAuthority);
        result.addAll(roleAuthority);

        return result.stream().sorted(String::compareTo).collect(Collectors.toList());
    }

    protected void remove(Relation.Type type, String id1, String id2) {
        Assert.notNull(id1, type.getId1Name() + " must not be null");
        Assert.notNull(id2, type.getId1Name() + " must not be null");
        Criteria criteria = new Criteria();
        criteria.and(type.getTypeName()).is(type)
                .and(type.getId1Name()).is(id1)
                .and(type.getId2Name()).is(id2);
        Query query = Query.query(criteria);
        this.deleteAll(this.find(query));
    }

    /**
     * 添加关系映射
     *
     * @param type                映射类型
     * @param id1                 主键1
     * @param id2                 主键2
     * @param duplicateKeyMessage 已存在数据信息
     */
    protected void add(Relation.Type type, String id1, String id2, String duplicateKeyMessage) {
        Assert.notNull(id1, type.getId1Name() + " must not be null");
        Assert.notNull(id2, type.getId1Name() + " must not be null");
        Relation relation = assignment(type, id1, id2);

        try {
            this.save(relation);
        } catch (DuplicateKeyException e) {
            if (log.isErrorEnabled()) {
                log.error(duplicateKeyMessage, e);
            }
            throw new BizException(BaseBizStatus.RECORD_EXISTS.message(duplicateKeyMessage));
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error(e.getMessage(), e);
            }
        }
    }

    private Relation assignment(Relation.Type type, String id1, String id2) {
        Relation relation = new Relation();
        relation.setType(type);
        switch (type) {
            case USER_ROLE:
                relation.setUserId(id1);
                relation.setRoleId(id2);
                break;
            case USER_AUTHORITY:
                relation.setUserId(id1);
                relation.setAuthorityId(id2);
                break;
            case USER_MENU:
                relation.setUserId(id1);
                relation.setMenuId(id2);
                break;
            case ROLE_MENU:
                relation.setRoleId(id1);
                relation.setMenuId(id2);
                break;
            case ROLE_AUTHORITY:
                relation.setRoleId(id1);
                relation.setAuthorityId(id2);
                break;
        }
        return relation;
    }

    /**
     * 获取用户相关联的主键ID
     *
     * @param userId 用过ID
     * @param type   关系类型
     * @return List<String>
     */
    protected List<String> getUserRelation(String userId, Relation.Type type) {
        Criteria criteria = new Criteria(type.getTypeName()).is(type);
        criteria.and(type.getId1Name()).is(userId)
                .and(type.getId2Name()).exists(true);
        Query query = Query.query(criteria);

        return this.find(query).stream().map(relation -> {
                    switch (type) {
                        case USER_ROLE:
                            return relation.getRoleId();
                        case USER_MENU:
                            return relation.getMenuId();
                        case USER_AUTHORITY:
                            return relation.getAuthorityId();
                        default:
                            return null;
                    }
                })
                .collect(Collectors.toList());
    }

    /**
     * 获取角色相关联的主键ID
     *
     * @param roleId 角色集合
     * @param type   关系类型
     * @return List<String>
     */
    protected List<String> getRoleRelation(Relation.Type type, String... roleId) {
        Criteria criteria = new Criteria(type.getTypeName()).is(type);
        criteria.and(type.getId1Name()).in(Arrays.asList(roleId))
                .and(type.getId2Name()).exists(true);
        Query query = Query.query(criteria);

        return this.find(query).stream().map(relation -> {
                    switch (type) {
                        case ROLE_MENU:
                            return relation.getMenuId();
                        case ROLE_AUTHORITY:
                            return relation.getAuthorityId();
                        default:
                            return null;
                    }
                })
                .collect(Collectors.toList());
    }

}
