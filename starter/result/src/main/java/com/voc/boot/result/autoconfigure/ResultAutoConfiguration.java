package com.voc.boot.result.autoconfigure;

import com.fasterxml.jackson.databind.Module;
import com.voc.boot.result.ResultAdvice;
import com.voc.boot.result.ResultErrorEndpoint;
import com.voc.boot.result.customizer.ResultPropertiesCustomizer;
import com.voc.boot.result.customizer.ResultPropertiesCustomizerBeanPostProcessor;
import com.voc.boot.result.json.ResultModule;
import com.voc.boot.result.json.ResultSerializer;
import com.voc.boot.result.properties.ResultProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 配置需要在 ErrorMvcAutoConfiguration 配置前进行，否则无法覆盖默认 BasicErrorController
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/23 12:38
 * @see ResultErrorEndpoint
 * @see ResultAdvice
 */
@Configuration
@Import({ResultAdvice.class, ResultErrorEndpoint.class})
@EnableConfigurationProperties({ResultProperties.class})
@AutoConfigureBefore(ErrorMvcAutoConfiguration.class)
public class ResultAutoConfiguration {

    /**
     * 自定义 Result 配置
     *
     * @param provider ObjectProvider<ResultPropertiesCustomizer>
     * @return ResultPropertiesCustomizerBeanPostProcessor
     */
    @Bean
    @ConditionalOnMissingBean
    ResultPropertiesCustomizerBeanPostProcessor resultPropertiesCustomizerBeanPostProcessor(ObjectProvider<ResultPropertiesCustomizer> provider) {
        return new ResultPropertiesCustomizerBeanPostProcessor(provider);
    }

    @Bean
    @ConditionalOnMissingBean
    ResultSerializer resultSerializer(ResultProperties resultProperties) {
        return new ResultSerializer(resultProperties);
    }

    @Bean
    Module resultModule(ResultSerializer resultSerializer){
        return new ResultModule(resultSerializer);
    }

}
