package net.jqsoft.result;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/26 22:27
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({JinQiResult.class})
public @interface EnableCompatibilityResult {
}
