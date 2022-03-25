package com.voc.restful.core.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/17 10:16
 */
@Configuration
@ConfigurationPropertiesScan("com.voc.restful.core")
@Import(RestfulCoreImportSelector.class)
public class AutoConfiguration {
}
