package com.voc.boot.cache;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;
import java.time.Duration;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/22 23:46
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CacheTTL {

    /**
     * 时间字符串
     * {@link Duration#parse(CharSequence)}
     *
     * @return 缓存过期时间
     */
    @AliasFor(attribute = "time")
    String value() default "";

    @AliasFor(attribute = "value")
    String time() default "";

    /**
     * {@link Duration#minus(Duration)}
     *
     * @return 缓存时间减去的时间
     */
    String minus() default "";

    /**
     * {@link Duration#plus(Duration)}
     *
     * @return 缓存时间叠加的时间
     */
    String plus() default "";
}
