package com.voc.system.autoconfigure;

import com.voc.system.SystemProperties;
import com.voc.system.service.IUserService;
import com.voc.system.service.impl.UserService;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/01 17:07
 */

@Configuration
@EnableConfigurationProperties(SystemProperties.class)
@ComponentScan(basePackages = {"com.voc.system"})
@AutoConfigureBefore({SecurityAutoConfiguration.class})
public class SystemAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    IUserService userService() {
        return new UserService();
    }


}
