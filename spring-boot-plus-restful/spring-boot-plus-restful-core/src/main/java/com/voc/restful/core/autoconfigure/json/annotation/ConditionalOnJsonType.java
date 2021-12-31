package com.voc.restful.core.autoconfigure.json.annotation;

import com.voc.restful.core.autoconfigure.json.JsonType;
import com.voc.restful.core.autoconfigure.condition.OnJsonTypeCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * JSON类型判断
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/31 11:02
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional({OnJsonTypeCondition.class})
public @interface ConditionalOnJsonType {

    JsonType value() default JsonType.JACKSON;

}
