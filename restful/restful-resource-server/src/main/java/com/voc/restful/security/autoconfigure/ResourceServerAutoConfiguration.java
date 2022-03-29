package com.voc.restful.security.autoconfigure;

import com.voc.restful.security.ResourceServerProperties;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/14 08:27
 */
@Configuration
@EnableConfigurationProperties({ResourceServerProperties.class})
@AutoConfigureAfter(SecurityAutoConfiguration.class)
public class ResourceServerAutoConfiguration {
}
