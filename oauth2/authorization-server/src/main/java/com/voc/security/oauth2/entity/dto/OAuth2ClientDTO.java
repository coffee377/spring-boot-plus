package com.voc.security.oauth2.entity.dto;

import com.voc.common.api.entity.impl.BaseEntity;
import com.voc.security.oauth2.entity.po.OAuth2ClientSettings;
import com.voc.security.oauth2.entity.po.OAuth2TokenSettings;
import com.voc.security.oauth2.enums.OAuth2ClientAuthenticationMethod;
import com.voc.security.oauth2.enums.OAuth2AuthorizationGrantType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.Set;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/12 08:57
 */
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class OAuth2ClientDTO extends BaseEntity {
    /**
     * 客户端名称
     */
    private String clientName;

    /**
     * 客户端认证方法
     */
    private Set<OAuth2ClientAuthenticationMethod> clientAuthenticationMethods;

    /**
     * 授权类型
     */
    private Set<OAuth2AuthorizationGrantType> authorizationGrantTypes;

    /**
     * 重定向地址
     */
    private Set<String> redirectUris;

    /**
     * 允许授予的权限
     */
    private Set<String> scopes;

    /**
     * 客户端设置
     */
    private OAuth2ClientSettings clientSettings;

    /**
     * 令牌设置
     */
    private OAuth2TokenSettings tokenSettings;
}
