package com.voc.dingtalk.annotation;

import java.lang.annotation.*;

/**
 * 主应用标识注解
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/20 10:07
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PrimaryApp {

}
