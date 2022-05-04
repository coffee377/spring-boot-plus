package com.voc.restful.security.service;

import com.baomidou.mybatisplus.annotation.TableName;
import com.voc.restful.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/21 13:26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "auth_user_bind")
public class AuthUserBind extends BaseEntity<String> {

    private String userId;

    private String type;

    private String unionId;

    private String openId;
}
