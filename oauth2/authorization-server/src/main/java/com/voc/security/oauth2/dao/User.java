package com.voc.security.oauth2.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import com.voc.common.api.dict.enums.UsingStatus;
import com.voc.security.oauth2.enums.OAuth2ClientAuthenticationMethod;
import lombok.Data;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/11/13 22:05
 */
@Data
@TableName(autoResultMap = true)
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private UsingStatus status;

//    @TableField(typeHandler = ClientAuthenticationMethodTypeHandler.class)
//    @TableField(typeHandler = UnknownTypeHandler.class)
    private List<OAuth2ClientAuthenticationMethod> authenticationMethods;
}
