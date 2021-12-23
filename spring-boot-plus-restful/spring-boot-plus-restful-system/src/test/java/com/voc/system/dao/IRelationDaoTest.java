package com.voc.system.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/15 09:02
 */
@Slf4j
@DisplayName("关系映射测试")
@EnableMongoRepositories
@SpringBootTest()
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class IRelationDaoTest {

    @Resource
    IRelationDao relationDao;

    private final String userId = "user1";
    private final List<String> roles = Arrays.asList("role1", "role2", "role3");
    private final List<String> menus = Arrays.asList("menu1", "menu2", "menu3", "menu4", "menu5", "menu6");
    private final List<String> authorities = Arrays.asList("authority1", "authority2", "authority3", "authority4", "authority5", "authority6");

    @Test
    @Order(1)
    void addRelation() {

        /* 角色添加菜单 */
        for (int i = 0; i < roles.size(); i++) {
            String role = roles.get(i);
            String m1 = menus.get(i * 2);
            String m2 = menus.get(i * 2 + 1);
            relationDao.addRoleMenuRelation(role, m1);
            relationDao.addRoleMenuRelation(role, m2);
        }

        /* 角色添加权限 */
        for (int i = 0; i < roles.size(); i++) {
            String role = roles.get(i);
            String a1 = authorities.get(i);
            String a2 = authorities.get(i + 3);
            relationDao.addRoleAuthorityIdRelation(role, a1);
            relationDao.addRoleAuthorityIdRelation(role, a2);
        }

        /* 用户添加角色 1,2 */
        roles.stream().filter(role -> !role.contains("3"))
                .forEach(role -> relationDao.addUserRoleRelation(userId, role));

        /* 用户添加菜单 5 */
        relationDao.addUserMenuRelation(userId, menus.get(4));

        /* 用户添加权限 3 */
        relationDao.addUserAuthorityRelation(userId, authorities.get(2));
    }

    @Test
    @Order(2)
    void getMenus() {
        List<String> menuIds = relationDao.getUserMenuIds(userId);
        Assertions.assertEquals(5, menuIds.size());
        Assertions.assertFalse(menuIds.contains(menus.get(5)));
    }

    @Test
    @Order(3)
    void getAuthority() {
        List<String> userAuthorityIds = relationDao.getUserAuthorityIds(userId);
        Assertions.assertEquals(5, userAuthorityIds.size());
        Assertions.assertFalse(userAuthorityIds.contains(authorities.get(5)));
    }

    @Test
    @Order(4)
    void removeRelation() {
        /* 角色移除菜单 */
        for (int i = 0; i < roles.size(); i++) {
            String role = roles.get(i);
            String m1 = menus.get(i * 2);
            String m2 = menus.get(i * 2 + 1);
            relationDao.removeRoleMenuRelation(role, m1);
            relationDao.removeRoleMenuRelation(role, m2);
        }

        /* 角色移除权限 */
        for (int i = 0; i < roles.size(); i++) {
            String role = roles.get(i);
            String a1 = authorities.get(i);
            String a2 = authorities.get(i + 3);
            relationDao.removeRoleAuthorityIdRelation(role, a1);
            relationDao.removeRoleAuthorityIdRelation(role, a2);
        }

        /* 用户添加角色 1,2 */
        roles.stream().filter(role -> !role.contains("3"))
                .forEach(role -> relationDao.removeUserRoleRelation(userId, role));

        /* 用户移除菜单 5 */
        relationDao.removeUserMenuRelation(userId, menus.get(4));

        /* 用户移除权限 3 */
        relationDao.removeUserAuthorityRelation(userId, authorities.get(2));
    }

}
