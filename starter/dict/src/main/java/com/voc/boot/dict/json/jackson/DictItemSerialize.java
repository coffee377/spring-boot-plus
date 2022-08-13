package com.voc.boot.dict.json.jackson;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.*;

/**
 * 字典项序列化注解
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/09 13:54
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@JsonSerialize(using = DictItemSerializer.class)
@JacksonAnnotationsInside
@Documented
public @interface DictItemSerialize {

    SerializeType type() default SerializeType.TEXT;

}
