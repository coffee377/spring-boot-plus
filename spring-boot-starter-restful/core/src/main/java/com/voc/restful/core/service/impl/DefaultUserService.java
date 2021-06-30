package com.voc.restful.core.service.impl;

import com.voc.restful.core.entity.IUser;
import com.voc.restful.core.entity.impl.BaseUser;
import com.voc.restful.core.service.UserService;
import com.voc.restful.core.third.ThirdApp;

import java.util.*;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/20 11:35
 */
public class DefaultUserService implements UserService<String> {

    private final Map<String, IUser<String>> users = new HashMap<>(3);
    private final Map<String, Set<String>> authorities = new HashMap<>(3);
    private final Map<ThirdApp, String> apps = new HashMap<>(1);

    public DefaultUserService() {
        /* 初始用户 */
        users.put("admin", new BaseUser<>("1", "admin", "{noop}123456"));
        users.put("demo", new BaseUser<>("2", "demo", "{noop}123456"));
        users.put("test", new BaseUser<>("3", "test", "{noop}123456"));

        /* 用户权限 */
        authorities.put("1", new HashSet<>(Arrays.asList("ROLE_ADMIN", "ROLE_KICKING")));
        authorities.put("2", new HashSet<>(Arrays.asList("ROLE_DEMO", "SCOPE_DEMO")));
        authorities.put("3", new HashSet<>(Arrays.asList("ROLE_TEST", "SCOPE_TEST")));

        /* 第三方应用绑定 */
        apps.put(ThirdApp.of(ThirdApp.DINGTALK, "GTf4M0xt0iPJmpLB4F9diiPgiEiE", "GtYDiST1IF5hoBQOGMlDdQgiEiE"), "1");
    }

    @Override
    public IUser<String> getUserByUsername(String username) {
        return users.get(username);
    }

    @Override
    public IUser<String> getUserByThirdApp(ThirdApp app) {
        ThirdApp third = apps.keySet().stream().filter(thirdApp ->
                thirdApp.getType().get().equals(app.getType().get())
                        && thirdApp.getUnionid().equals(app.getUnionid())
                        && thirdApp.getOpenid().equals(app.getOpenid())
        ).findFirst().orElse(null);

        String uid = apps.get(third);
        return users.values().stream().filter(user -> user.getId().equals(uid)).findFirst().orElse(null);
    }

    @Override
    public Set<String> getAuthorities(String uid) {
        return authorities.get(uid);
    }
}
