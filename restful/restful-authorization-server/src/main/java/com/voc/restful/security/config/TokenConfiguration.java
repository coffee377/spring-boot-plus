package com.voc.restful.security.config;

import com.voc.restful.security.helper.KeyGenerator;
import com.voc.restful.security.helper.SnowflakeHelper;
import com.voc.restful.security.token.AccessTokenGenerator;
import com.voc.restful.security.token.RefreshTokenGenerator;
import com.voc.restful.security.token.TokenProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenCustomizer;
import org.springframework.security.oauth2.server.authorization.token.*;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/02 21:42
 */
@Configuration
@EnableConfigurationProperties(TokenProperties.class)
public class TokenConfiguration {
    private final static ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    @Bean("snowflakeHelper")
    @ConditionalOnMissingBean(SnowflakeHelper.class)
    public SnowflakeHelper snowflakeHelper() {
        return new SnowflakeHelper(RANDOM.nextInt(0, 1023));
    }

    @Bean
    public KeyGenerator keyGenerator(SnowflakeHelper snowflakeHelper) {
        return new KeyGenerator(snowflakeHelper);
    }

//    @Bean
    public OAuth2TokenGenerator tokenGenerator(@Autowired(required = false) JwtGenerator jwtGenerator,
                                               @Autowired(required = false) OAuth2TokenCustomizer<OAuth2TokenClaimsContext> tokenCustomizer) {
        OAuth2TokenGenerator tokenGenerator;
        AccessTokenGenerator accessTokenGenerator = new AccessTokenGenerator();
        if (tokenCustomizer != null) {
            accessTokenGenerator.setAccessTokenCustomizer(tokenCustomizer);
        }
        RefreshTokenGenerator refreshTokenGenerator = new RefreshTokenGenerator();
        if (jwtGenerator != null) {
            tokenGenerator = new DelegatingOAuth2TokenGenerator(
                    jwtGenerator, accessTokenGenerator, refreshTokenGenerator);
        } else {
            tokenGenerator = new DelegatingOAuth2TokenGenerator(
                    accessTokenGenerator, refreshTokenGenerator);
        }
        return tokenGenerator;
    }

}
