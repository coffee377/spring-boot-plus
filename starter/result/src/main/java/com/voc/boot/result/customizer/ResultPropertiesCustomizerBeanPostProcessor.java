package com.voc.boot.result.customizer;

import com.voc.boot.result.properties.ResultProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.util.LambdaSafe;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/26 23:29
 */
public class ResultPropertiesCustomizerBeanPostProcessor implements BeanPostProcessor {
    private final List<ResultPropertiesCustomizer> customizers;

    public ResultPropertiesCustomizerBeanPostProcessor(ObjectProvider<ResultPropertiesCustomizer> provider) {
        customizers = provider.orderedStream().collect(Collectors.toList());
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ResultProperties) {
            this.postProcessBeforeInitialization((ResultProperties) bean);
        }
        return bean;
    }

    private void postProcessBeforeInitialization(ResultProperties resultProperties) {
        LambdaSafe.Callbacks<ResultPropertiesCustomizer, ResultProperties> callbacks =
                LambdaSafe.callbacks(ResultPropertiesCustomizer.class, customizers, resultProperties);
        callbacks.invoke(customizer -> {
            customizer.customize(resultProperties);
        });
    }
}
