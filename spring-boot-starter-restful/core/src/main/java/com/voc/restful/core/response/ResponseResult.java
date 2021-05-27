package com.voc.restful.core.response;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/05/27 18:53
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseResult {

    /**
     * 响应结果是否自动包装
     *
     * @return boolean
     */
    @AliasFor("wrapped")
    boolean value() default true;

    /**
     * 响应结果是否自动包装
     *
     * @return boolean
     */
    @AliasFor("value")
    boolean wrapped() default true;

}
