package com.voc.restful.core.autoconfigure.cache;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.voc.restful.core.props.RedisCacheProperties;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
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
@Configuration
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties(RedisCacheProperties.class)
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
    public RedisSerializer<?> redisValueSerializer() {
        /* 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值 */
        ObjectMapper objectMapper = new ObjectMapper();
        PolymorphicTypeValidator typeValidator = objectMapper.getPolymorphicTypeValidator();
        objectMapper.activateDefaultTyping(typeValidator, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

//        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(mapper);
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        serializer.setObjectMapper(objectMapper);
        return serializer;
    }

    /**
     * 默认配置
     *
     * @param redisValueSerializer RedisSerializer<Object>
     * @return RedisCacheConfiguration
     * @see #redisValueSerializer()
     */
    @Bean("redisCacheConfiguration")
    public RedisCacheConfiguration redisCacheConfiguration(RedisCacheProperties cacheProperties,
                                                           RedisSerializer<Object> redisValueSerializer) {
        CacheProperties.Redis redis = cacheProperties.getRedis();
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();

        configuration = configuration
                /* 覆盖默认的双冒号前缀配置 */
                .computePrefixWith(singleColon)
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisValueSerializer));

        /* 设置缓存的默认过期时 */
        Duration duration = redis.getTimeToLive();
        if (duration == null) {
            configuration = configuration.entryTtl(Duration.ofSeconds(30));
        } else {
            configuration = configuration.entryTtl(duration);
        }

        if (redis.getKeyPrefix() != null) {
            configuration = configuration.prefixCacheNameWith(redis.getKeyPrefix());
        }

        /* 不缓存空值 */
        if (!redis.isCacheNullValues()) {
            configuration = configuration.disableCachingNullValues();
        }

        if (!redis.isUseKeyPrefix()) {
            configuration = configuration.disableKeyPrefix();
        }

        return configuration;
    }

    /**
     * 配置自定义redisTemplate
     *
     * @param connectionFactory RedisConnectionFactory
     * @return RedisTemplate<String, Object>
     * @see RedisAutoConfiguration#redisTemplate(RedisConnectionFactory)
     */
    @Bean("redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory, RedisSerializer<Object> redisValueSerializer) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        /* 使用StringRedisSerializer来序列化和反序列化redis的key值 */
        template.setKeySerializer(StringRedisSerializer.UTF_8);
        template.setHashKeySerializer(StringRedisSerializer.UTF_8);
        template.setValueSerializer(redisValueSerializer);
        template.setHashValueSerializer(redisValueSerializer);
        template.afterPropertiesSet();
        return template;
    }

}
