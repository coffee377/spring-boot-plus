package com.voc.restful.security.oauth2.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.voc.restful.core.entity.IUser;
import com.voc.restful.core.entity.impl.BaseUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/21 13:26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "oauth2_account", excludeProperty = "authorities")
public class Account extends BaseUser<String> implements IUser<String> {
    /**
     * 工号
     */
    private String jobNumber;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户头像
     */
    private String avatar;
}
