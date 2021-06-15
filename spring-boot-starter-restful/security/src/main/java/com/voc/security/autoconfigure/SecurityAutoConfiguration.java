package com.voc.security.autoconfigure;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/14 08:27
 */
@Configuration
@Import({SecurityImport.class, BeanConfig.class, WebSecurityConfig.class})
public class SecurityAutoConfiguration {
}
