package com.voc.restful.security.token;

import com.voc.restful.core.util.SpringUtils;
import com.voc.restful.security.helper.SnowflakeHelper;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.oauth2.core.OAuth2TokenType;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/05 20:58
 */
public class TokenStringKeyGenerator implements StringKeyGenerator {
    private final SnowflakeHelper snowflakeHelper;
    private final MessageDigestPasswordEncoder sha256 = new MessageDigestPasswordEncoder("SHA-256");

    private OAuth2TokenType tokenType = OAuth2TokenType.ACCESS_TOKEN;

    public TokenStringKeyGenerator() {
        this.snowflakeHelper = SpringUtils.getBean(SnowflakeHelper.class);
    }

    public void setTokenType(OAuth2TokenType tokenType) {
        this.tokenType = tokenType;
    }

    @Override
    public String generateKey() {
        long id = snowflakeHelper.nextId();
        String hex = Long.toHexString(id);
        if (tokenType.equals(TokenType.AUTHORIZATION_CODE_TOKEN)) {
            return hex;
        }
        return sha256.encode(hex).replaceFirst("\\{.*}", "");
    }
}
