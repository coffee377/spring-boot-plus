package com.voc.restful.core.listener;

import com.voc.restful.core.banner.YamlPropertySourceFactory;
import com.voc.restful.core.props.AppInfoProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/17 14:51
 */
@Slf4j
public class AppInfoEnvironmentPostProcessor implements EnvironmentPostProcessor {
    private static final PropertySourceFactory DEFAULT_PROPERTY_SOURCE_FACTORY = new YamlPropertySourceFactory();
    private final List<String> propertySourceNames = new ArrayList<>();

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        PropertySource propertySource = AnnotationUtils.findAnnotation(AppInfoProperties.class, PropertySource.class);
        try {
            if (propertySource != null) {
                this.processPropertySource(environment, application, propertySource);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private void processPropertySource(ConfigurableEnvironment environment, SpringApplication application,
                                       PropertySource propertySource) throws Exception {
        String name = propertySource.name();
        if (!StringUtils.hasLength(name)) {
            name = null;
        }

        String encoding = propertySource.encoding();
        if (!StringUtils.hasLength(encoding)) {
            encoding = null;
        }

        String[] locations = propertySource.value();
        Assert.isTrue(locations.length > 0, "At least one @PropertySource(value) location is required");
        boolean ignoreResourceNotFound = propertySource.ignoreResourceNotFound();
        Class<? extends PropertySourceFactory> factoryClass = propertySource.factory();
        PropertySourceFactory factory = factoryClass == PropertySourceFactory.class ? DEFAULT_PROPERTY_SOURCE_FACTORY : BeanUtils.instantiateClass(factoryClass);

        for (String location : locations) {
            try {
                String resolvedLocation = environment.resolveRequiredPlaceholders(location);
                DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
                Resource resource = resourceLoader.getResource(resolvedLocation);
                this.addPropertySource(factory.createPropertySource(name, new EncodedResource(resource, encoding)), environment);
            } catch (IllegalArgumentException | IOException exception) {
                if (!ignoreResourceNotFound) {
                    throw exception;
                }

                if (log.isInfoEnabled()) {
                    log.info("Properties location [{}] not resolvable: {}", location, exception.getMessage());
                }
            }
        }

    }

    private void addPropertySource(org.springframework.core.env.PropertySource<?> propertySource, ConfigurableEnvironment environment) {
        String name = propertySource.getName();
        MutablePropertySources propertySources = environment.getPropertySources();
        if (this.propertySourceNames.contains(name)) {
            org.springframework.core.env.PropertySource<?> existing = propertySources.get(name);
            if (existing != null) {
                org.springframework.core.env.PropertySource<?> newSource = propertySource instanceof ResourcePropertySource ? ((ResourcePropertySource) propertySource).withResourceName() : propertySource;
                if (existing instanceof CompositePropertySource) {
                    ((CompositePropertySource) existing).addFirstPropertySource(newSource);
                } else {
                    if (existing instanceof ResourcePropertySource) {
                        existing = ((ResourcePropertySource) existing).withResourceName();
                    }

                    CompositePropertySource composite = new CompositePropertySource(name);
                    composite.addPropertySource(newSource);
                    composite.addPropertySource(existing);
                    propertySources.replace(name, composite);
                }

                return;
            }
        }

        if (this.propertySourceNames.isEmpty()) {
            propertySources.addLast(propertySource);
        } else {
            String firstProcessed = this.propertySourceNames.get(this.propertySourceNames.size() - 1);
            propertySources.addBefore(firstProcessed, propertySource);
        }

        this.propertySourceNames.add(name);
    }
}
