package com.voc.restful.security.token;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/15 17:07
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "api.security.token")
public class TokenProperties {

    /**
     * 是否允许 POST 请求传递 access_token
     */
    private boolean allowFormEncodedBodyParameter = false;

    /**
     * 是否允许 GET 请求传递 access_token
     */
    private boolean allowUriQueryParameter = true;

    /**
     * 含有 token 的请求头名称
     */
    private String bearerTokenHeaderName = "Authorization";

    /**
     * 是否启用双令牌机制
     */
    private boolean dualToken = true;

    /**
     * access_token 有效时间，默认 2h
     */
    private Duration accessTokenExpiresIn = Duration.ofHours(2);

    /**
     * refresh_token 有效时间，默认 30d
     */
    private Duration refreshTokenExpiresIn = Duration.ofDays(30);


    /**
     * 在线令牌并发数
     */
    int concurrency = 1;

    /**
     * 在线令牌超过并发数使用的驱逐策略
     */
    TokenConcurrencyStrategy concurrencyStrategy = TokenConcurrencyStrategy.EVICT_LATEST;

    /**
     * 缓存不存在时令牌无效
     *
     * @see com.voc.restful.security.token.jwt.JwtRedisCacheTokenValidator
     */
    boolean invalidWhenCacheNotExist;

    /**
     * TOKEN 驱逐策略
     */
    enum TokenConcurrencyStrategy {
        NONE,
        EVICT_EARLIEST,
        EVICT_LATEST
    }

    /**
     * TOKEN 类型
     */
    enum TokenType {
        JWT, OPAQUE
    }

}
