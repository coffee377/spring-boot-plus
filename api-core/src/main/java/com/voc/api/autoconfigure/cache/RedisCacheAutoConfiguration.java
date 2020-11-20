package com.voc.api.autoconfigure.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/18 16:03
 */
@EnableCaching
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisCacheAutoConfiguration {

    /**
     * 覆盖默认的缓存前缀
     *
     * @see CacheKeyPrefix#simple()
     */
    private final CacheKeyPrefix singleColon = (name) -> name + ":";

    /**
     * 自定义 json 序列化
     *
     * @return RedisSerializer<Object>
     */
    @Bean("redisValueSerializer")
    public RedisSerializer<Object> redisValueSerializer(ObjectMapper mapper) {
        /* 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值 */
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        serializer.setObjectMapper(mapper);
        return serializer;
    }

    /**
     * 默认配置
     *
     * @param redisValueSerializer RedisSerializer<Object>
     * @return RedisCacheConfiguration
     * @see #redisValueSerializer(ObjectMapper)
     */
    @Bean("redisCacheConfiguration")
    public RedisCacheConfiguration redisCacheConfiguration(RedisSerializer<Object> redisValueSerializer) {
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        configuration = configuration
                /* 覆盖默认的双冒号前缀配置 */
                .computePrefixWith(singleColon)
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisValueSerializer))
                /* 不缓存空值 */
                .disableCachingNullValues()
                /* 设置缓存的默认过期时间，也是使用Duration设置 */
                .entryTtl(Duration.ofSeconds(30));
        return configuration;
    }

    /**
     * 配置自定义redisTemplate
     *
     * @param connectionFactory RedisConnectionFactory
     * @return RedisTemplate<String, Object>
     */
    @Bean("redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory, RedisSerializer<Object> redisValueSerializer) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setValueSerializer(redisValueSerializer);
        /* 使用StringRedisSerializer来序列化和反序列化redis的key值 */
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(redisValueSerializer);
        template.afterPropertiesSet();
        return template;
    }

}
