package com.voc.boot.cache;

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
    String value() default "";

}
