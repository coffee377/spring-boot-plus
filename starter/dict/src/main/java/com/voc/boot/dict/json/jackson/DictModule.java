package com.voc.boot.dict.json.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.voc.common.api.dict.DictionaryItem;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/09 14:05
 */
public class DictModule extends SimpleModule implements EnvironmentAware  {

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }


    public DictModule() {
        super("DictModule");
    }

    @PostConstruct
    private void init() {
        DictSerializeProperties serializeProperties =
                Binder.get(environment).bind("dict.serialize", DictSerializeProperties.class).orElse(new DictSerializeProperties());
        this.addSerializer(DictionaryItem.class, new DictItemSerializer(serializeProperties));
    }

    @Override
    public void setupModule(SetupContext context) {
        super.setupModule(context);
    }
}
