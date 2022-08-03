package com.voc.boot.result.annotation;

import com.voc.boot.result.Result;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 自动包装、拆包注解
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/05/27 18:53
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseResult {

    /**
     * 响应结果是否自动包装成 {@link Result}
     *
     * @return boolean
     */
    @AliasFor("wrapped")
    boolean value() default true;

    /**
     * @return boolean
     * @see ResponseResult#value()
     */
    @AliasFor("value")
    boolean wrapped() default true;

    /**
     * 是否原始数据
     *
     * <p>
     * 若 Controller 返回类型为 {@link com.voc.boot.result.Result}，则返回 {@link  Result#getData()} 数据
     * </p>
     *
     * @return boolean
     */
    boolean original() default false;

}
