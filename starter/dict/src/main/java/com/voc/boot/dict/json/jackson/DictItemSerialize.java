package com.voc.boot.dict.json.jackson;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 字典项局部序列化注解
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/09 13:54
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@JsonSerialize(using = DictItemSerializer.class, converter = DictItemConverter.class)
@JacksonAnnotationsInside
@Documented
public @interface DictItemSerialize {

    @AliasFor("type")
    SerializeType[] value() default SerializeType.VALUE;

    @AliasFor("value")
    SerializeType[] type() default SerializeType.VALUE;

}
