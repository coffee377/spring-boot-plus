package com.voc.api.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/19 12:15
 */
@Slf4j
public class BeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar, BeanFactoryAware {
    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (beanFactory instanceof ConfigurableListableBeanFactory) {
            this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
        }
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        log.warn("registerBeanDefinitions");
        String[] beanNamesForType = this.beanFactory.getBeanNamesForType(Result.class, true, false);
        RootBeanDefinition beanDefinition = new RootBeanDefinition(Result.class);
        registry.registerBeanDefinition("result", beanDefinition);
    }
}
