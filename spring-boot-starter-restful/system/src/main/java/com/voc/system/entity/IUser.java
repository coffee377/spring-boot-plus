package com.voc.system.entity;

import com.voc.restful.core.bean.IBean;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/02/05 08:53
 */
public interface IUser extends IBean {
    /**
     * 获取用户ID
     *
     * @return String
     */
    String getId();

    /**
     * 获取用户名
     *
     * @return String
     */
    String getUsername();

//    private String id;
//    private String username;
//    private String password;
//    private String realName;
//    private String telephone;
//    private String email;
}
