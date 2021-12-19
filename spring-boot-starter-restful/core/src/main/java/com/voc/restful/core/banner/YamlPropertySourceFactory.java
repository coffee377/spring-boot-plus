package com.voc.restful.core.banner;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/17 12:58
 */
public class YamlPropertySourceFactory extends DefaultPropertySourceFactory implements PropertySourceFactory {

    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource encodedResource) throws IOException {
        if (isYaml(encodedResource)) {
            return createYamlPropertySource(name, encodedResource);
        }
        return super.createPropertySource(name, encodedResource);
    }

    protected PropertySource<?> createYamlPropertySource(String name, EncodedResource encodedResource) throws IOException {
        String sourceName = StringUtils.hasLength(name) ? name : encodedResource.getResource().getFilename();
        YamlPropertySourceLoader loader = new YamlPropertySourceLoader();
        List<PropertySource<?>> load = loader.load(sourceName, encodedResource.getResource());
        return load.get(0);
    }


    private boolean isYaml(EncodedResource encodedResource) {
        String filename = encodedResource.getResource().getFilename();
        return StringUtils.hasLength(filename) && filename.matches(".*y(a)?ml$");
    }

    private Properties loadYamlIntoProperties(EncodedResource encodedResource) {
        YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
        factoryBean.setResources(encodedResource.getResource());
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }
}
