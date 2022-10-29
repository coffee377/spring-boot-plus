package net.jqsoft.result;

import com.voc.boot.result.properties.ResultWrapper;
import org.springframework.context.annotation.Configuration;
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
@Configuration
public @interface EnableResult {

    /**
     * 是否兼容老代码，即不改变已有代码的逻辑
     * <p>如果兼容老代码，则 {@link ResultWrapper#isEnable()} 为 false，否则为 true</p>
     *
     * @return true or false
     */
    boolean compatibility() default false;
}
