package com.voc.common.api.dict;

import java.lang.annotation.*;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/07/27 22:43
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Dict {

    /**
     * 字典编码
     *
     * @return String
     */
    String code() default "";

    /**
     * 字典名称
     *
     * @return String
     */
    String name() default "";

}
