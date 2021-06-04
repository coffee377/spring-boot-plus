package com.voc.restful.core.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/02 16:37
 */
@Getter
@Setter
@ConfigurationProperties(
        prefix = "spring.cache"
)
public class RedisCacheProperties {

    private final CacheProperties.Redis redis;

    public RedisCacheProperties() {
        redis = new CacheProperties.Redis();
        redis.setCacheNullValues(false);
        redis.setEnableStatistics(true);
    }


}
