package com.voc.boot.dict.json.jackson;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.voc.boot.dict.json.annotation.DictSerialize;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.annotation.Annotation;

public class DictJacksonAnnotationIntrospector extends JacksonAnnotationIntrospector {
    @Override
    protected <A extends Annotation> A _findAnnotation(Annotated ann, Class<A> annoClass) {
        if (ann.getAnnotated() != null && ann.getAnnotated().isAnnotationPresent(DictSerialize.class) && annoClass.isAssignableFrom(DictSerialize.class)) {
//            final JsonIgnore jsonIgnore = AnnotatedElementUtils.findMergedAnnotation(ann.getAnnotated(), JsonIgnore.class);
//            final boolean isJsonIgnore = annoClass.isAssignableFrom(JsonIgnore.class);
            final A a = AnnotatedElementUtils.findMergedAnnotation(ann.getAnnotated(), annoClass);
//            if (isJsonIgnore && a != null) {
//                return a;
//            } else if (!isJsonIgnore && a != null) {
//                if (jsonIgnore != null && jsonIgnore.value()) {
//                    return null;
//                }
//                return a;
//            }
            return a;
        }
        return super._findAnnotation(ann, annoClass);
    }

    @Override
    public Object findSerializer(Annotated annotated) {
//        annotated.getAnnotation(DictSerialize.class)
        DictSerialize dictSerialize = _findAnnotation(annotated, DictSerialize.class);
        if (dictSerialize != null) {
            return new DictItemSerializer(null, null);
        }
        return super.findSerializer(annotated);
    }
}
