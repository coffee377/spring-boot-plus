package com.voc.boot.result.autoconfigure;

import com.voc.boot.result.ResultAdvice;
import com.voc.boot.result.ResultErrorController;
import com.voc.boot.result.json.ResultJackson2ObjectMapperBuilder;
import com.voc.boot.result.json.ResultSerializer;
import com.voc.boot.result.properties.ResultProperties;
import com.voc.boot.result.properties.ResultWrapper;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * 配置需要在 ErrorMvcAutoConfiguration 配置前进行，否则无法覆盖默认 BasicErrorController
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/23 12:38
 * @see ResultErrorController
 * @see ResultAdvice
 */
@Configuration
@Import({ResultAdvice.class, ResultErrorController.class})
@EnableConfigurationProperties({ResultProperties.class, ResultWrapper.class})
@AutoConfigureBefore(ErrorMvcAutoConfiguration.class)
public class ResultAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    ResultSerializer resultSerializer(ResultProperties resultProperties) {
        return new ResultSerializer(resultProperties);
    }

    @Bean
    @ConditionalOnClass(Jackson2ObjectMapperBuilder.class)
    ResultJackson2ObjectMapperBuilder resultJackson2ObjectMapperBuilder(ResultSerializer resultSerializer) {
        return new ResultJackson2ObjectMapperBuilder(resultSerializer);
    }

}
