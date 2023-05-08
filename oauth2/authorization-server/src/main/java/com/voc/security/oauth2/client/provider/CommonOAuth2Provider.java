package com.voc.security.oauth2.client.provider;

import com.voc.common.api.biz.BizException;
import com.voc.common.api.biz.InternalBizStatus;
import com.voc.security.oauth2.client.dingtalk.DingTalkOAuth2User;
import com.voc.security.oauth2.OAuth2Provider;
import lombok.Getter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthenticationMethod;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/30 21:55
 */
@Getter
public enum CommonOAuth2Provider implements OAuth2Provider {

    GITHUB("GitHub") {
        @Override
        public ClientRegistration.Builder getBuilder(String registrationId) {
            ClientRegistration.Builder builder = getBuilder(registrationId,
                    ClientAuthenticationMethod.CLIENT_SECRET_BASIC, DEFAULT_REDIRECT_URL);
            builder.scope("read:user");
            builder.authorizationUri("https://github.com/login/oauth/authorize");
            builder.tokenUri("https://github.com/login/oauth/access_token");
            builder.userInfoUri("https://api.github.com/user");
            builder.userNameAttributeName("id");
            builder.clientName("GitHub");
            return builder;
        }
    },

    /**
     * <p>1. <a href="https://open.dingtalk.com/document/orgapp-server/obtain-identity-credentials">获取访问凭证</a></p>
     * <p>2. <a href="https://open.dingtalk.com/document/orgapp-server/obtain-user-token">获取用户访问令牌</a></p>
     * <p>3.
     * <a href="https://open.dingtalk.com/document/isvapp-server/dingtalk-retrieve-user-information">获取通讯录个人信息</a></p>
     */
    DINGTALK("钉钉") {
        @Override
        public ClientRegistration.Builder getBuilder(String registrationId) {
            ClientRegistration.Builder builder = getBuilder(registrationId, ClientAuthenticationMethod.CLIENT_SECRET_POST, DEFAULT_REDIRECT_URL);
            builder.authorizationUri("https://login.dingtalk.com/oauth2/auth");
            builder.tokenUri("https://api.dingtalk.com/v1.0/oauth2/userAccessToken");
            builder.userInfoUri("https://api.dingtalk.com/v1.0/contact/users/me");
            builder.userInfoAuthenticationMethod(AuthenticationMethod.HEADER);
            builder.userNameAttributeName(DingTalkOAuth2User.NAME_ATTRIBUTE_KEY);
            // TODO: 2022/10/30 22:11 钉钉是非标准的 oauth2.1 授权协议，若传 openid 则走 oidc 认证
            builder.scope("corpid");
            builder.clientName("DingTalk");
            return builder;
        }
    },

    ALIPAY("支付宝") {
        @Override
        public ClientRegistration.Builder getBuilder(String registrationId) {
            // TODO: 待实现
            throw new BizException(InternalBizStatus.UN_IMPLEMENTED_METHOD);
        }
    },

    WECHAT("微信") {
        @Override
        public ClientRegistration.Builder getBuilder(String registrationId) {
            // TODO: 待实现
            throw new BizException(InternalBizStatus.UN_IMPLEMENTED_METHOD);
        }
    },

    QQ("QQ") {
        @Override
        public ClientRegistration.Builder getBuilder(String registrationId) {
            // TODO: 待实现
            throw new BizException(InternalBizStatus.UN_IMPLEMENTED_METHOD);
        }
    },

    /**
     * <a href="https://gitee.com/api/v5/oauth_doc">OAuth 文档</a>
     */
    GITEE("Gitee") {
        @Override
        public ClientRegistration.Builder getBuilder(String registrationId) {
            ClientRegistration.Builder builder = getBuilder(registrationId, ClientAuthenticationMethod.CLIENT_SECRET_POST, DEFAULT_REDIRECT_URL);
            builder.authorizationUri("https://gitee.com/oauth/authorize");
            builder.tokenUri("https://gitee.com/oauth/token");
            builder.userInfoUri("https://gitee.com/api/v5/user");
            builder.userInfoAuthenticationMethod(AuthenticationMethod.QUERY);
            builder.userNameAttributeName("name");
            return builder;
        }
    },

    NATIVE("") {
        @Override
        public ClientRegistration.Builder getBuilder(String registrationId) {
            ClientRegistration.Builder builder = getBuilder(registrationId, ClientAuthenticationMethod.CLIENT_SECRET_POST, DEFAULT_REDIRECT_URL);
            builder.issuerUri("{baseUrl}");
            return builder;
        }
    };

    private final String description;

    CommonOAuth2Provider(String description) {
        this.description = description;
    }

    public String getName() {
        return name().toLowerCase();
    }

    public String getDescription() {
        return description;
    }

    protected final ClientRegistration.Builder getBuilder(String registrationId, ClientAuthenticationMethod method, String redirectUri) {
        ClientRegistration.Builder builder = ClientRegistration.withRegistrationId(registrationId);
        builder.clientAuthenticationMethod(method);
        builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
        builder.redirectUri(redirectUri);
        builder.clientName(getName());
        return builder;
    }

}
