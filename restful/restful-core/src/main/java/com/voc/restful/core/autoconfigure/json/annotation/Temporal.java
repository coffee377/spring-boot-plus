package com.voc.restful.core.autoconfigure.json.annotation;

import com.voc.restful.core.autoconfigure.json.JsonType;

import java.lang.annotation.*;

/**
 * 标记需要被 Spring 管理的对象
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/20 15:36
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Temporal {

    JsonType value() default JsonType.JACKSON;

}
