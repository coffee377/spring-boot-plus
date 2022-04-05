package com.voc.restful.security.helper;

import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.util.Assert;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/05 11:41
 */
@Deprecated
public class KeyGenerator {

    private final SnowflakeHelper snowflake;
    private final MessageDigestPasswordEncoder sha256 = new MessageDigestPasswordEncoder("SHA-256");

    public KeyGenerator(SnowflakeHelper snowflake) {
        Assert.notNull(snowflake, "snowflake can not be null");
        this.snowflake = snowflake;
    }

    public String hex() {
        return Long.toHexString(snowflake.nextId());
    }

    public String sha256() {
        String id = hex();
        return sha256.encode(id).replaceFirst("\\{.*}", "");
    }

}
