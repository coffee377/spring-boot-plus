package com.voc.oauth2.api.model;

import com.voc.common.api.bean.Identify;
import lombok.Data;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/11 09:58
 */
@Data
public class Account implements Identify<String> {
    /**
     * 账号 ID
     */
    private String id;

    /**
     * 账号名称
     */
    private String username;

    /**
     * 账号密码
     */
    private transient String password;

    /**
     * 工号
     */
    private String jobNumber;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String avatar;

}
