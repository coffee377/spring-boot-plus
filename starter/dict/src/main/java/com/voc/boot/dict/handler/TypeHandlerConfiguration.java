package com.voc.boot.dict.handler;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @since 0.1.2
 */
@Configuration
@Import({MyBatisTypeHandlerConfiguration.class, MyBatisPlusTypeHandlerConfiguration.class})
public class TypeHandlerConfiguration {
}
