package com.voc.boot.dict.json.jackson;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.core.Ordered;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.function.Function;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/09 14:08
 */
public class DictJackson2ObjectMapperBuilder implements Jackson2ObjectMapperBuilderCustomizer, Ordered {

    @Override
    public void customize(Jackson2ObjectMapperBuilder builder) {
        builder.annotationIntrospector(new DictJacksonAnnotationIntrospector());
        builder.annotationIntrospector(annotationIntrospector -> {
            AnnotationIntrospector pair = AnnotationIntrospectorPair.pair(annotationIntrospector, new DictJacksonAnnotationIntrospector());
            return pair;
        });
//        builder.
//        DictModule dictModule = new DictModule();
//        builder.modules(dictModule);
//        builder.serializers(new DictItemSerializer(new DictItemSerializeProperties()));
    }


    @Override
    public int getOrder() {
        return 1;
    }
}
