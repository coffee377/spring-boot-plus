package com.voc.security.oauth2.servcer;

import org.springframework.lang.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;

import java.util.Map;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/31 23:53
 */
public class OAuth2UsernamePasswordAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {
    public final static String KEY = "key";
    public final static String CODE = "code";

    /**
     * 用户名
     */
    private final String username;

    /**
     * 密码
     */
    private final String password;

    /**
     * 验证码缓存的 key
     */
    private String key;

    /**
     * 验证码
     */
    private String code;


    public OAuth2UsernamePasswordAuthenticationToken(String username, String password, Authentication clientPrincipal,
                                                     @Nullable Map<String, Object> additionalParameters) {
        super(AuthorizationGrantType.PASSWORD, clientPrincipal, additionalParameters);
        this.username = username;
        this.password = password;
        if (additionalParameters != null) {
            key = (String) additionalParameters.get(KEY);
            code = (String) additionalParameters.get(CODE);
        }

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    @Nullable
    public String getKey() {
        return key;
    }

    @Nullable
    public String getCode() {
        return code;
    }

    public static OAuth2UsernamePasswordAuthenticationToken unauthenticated(String username, String password, Authentication clientPrincipal,
                                                                            @Nullable Map<String, Object> additionalParameters) {
        return new OAuth2UsernamePasswordAuthenticationToken(username, password, clientPrincipal, additionalParameters);
    }

    public static OAuth2UsernamePasswordAuthenticationToken authenticated(String username, String password, Authentication clientPrincipal,
                                                                            @Nullable Map<String, Object> additionalParameters) {
        return new OAuth2UsernamePasswordAuthenticationToken(username, password, clientPrincipal, additionalParameters);
    }
}
