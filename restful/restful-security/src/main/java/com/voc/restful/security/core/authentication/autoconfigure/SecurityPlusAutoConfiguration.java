package com.voc.restful.security.core.authentication.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/14 08:27
 */
@Configuration
//@EnableConfigurationProperties({SecurityProperties.class})
@AutoConfigureAfter(SecurityAutoConfiguration.class)
@Import({SecurityImport.class})
public class SecurityPlusAutoConfiguration {
}
