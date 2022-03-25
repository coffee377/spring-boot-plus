package com.voc.restful.core.autoconfigure.condition;

import com.voc.restful.core.autoconfigure.json.JsonType;
import com.voc.restful.core.autoconfigure.json.annotation.ConditionalOnJsonType;
import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertyResolver;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/31 11:05
 */
public class OnJsonTypeCondition extends SpringBootCondition {
    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        AnnotationAttributes annotationAttributes = annotationAttributesFromMap(metadata);
        Environment environment = context.getEnvironment();
        return determineOutcome(annotationAttributes, environment);
    }

    private AnnotationAttributes annotationAttributesFromMap(AnnotatedTypeMetadata metadata) {
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(ConditionalOnJsonType.class.getName());
        return AnnotationAttributes.fromMap(annotationAttributes);
    }

    private List<AnnotationAttributes> annotationAttributesFromMultiValueMap(MultiValueMap<String, Object> multiValueMap) {
        List<Map<String, Object>> maps = new ArrayList<>();
        multiValueMap.forEach((key, value) -> {
            for (int i = 0; i < value.size(); i++) {
                Map<String, Object> map;
                if (i < maps.size()) {
                    map = maps.get(i);
                } else {
                    map = new HashMap<>(0);
                    maps.add(map);
                }
                map.put(key, value.get(i));
            }
        });

        return maps.stream().map(AnnotationAttributes::fromMap).collect(Collectors.toList());
    }

    private ConditionOutcome determineOutcome(AnnotationAttributes annotationAttributes, PropertyResolver resolver) {
        if (annotationAttributes != null) {
            JsonType value = annotationAttributes.getEnum("value");
            JsonType jsonType = resolver.getProperty("api.json.type", JsonType.class);

            if (jsonType == null && JsonType.JACKSON.equals(value)) {
                return ConditionOutcome.match(ConditionMessage.forCondition(ConditionalOnJsonType.class)
                        .because("api.json.type hase default value " + JsonType.JACKSON.name()));
            }
            if (jsonType != null && jsonType.equals(value)) {
                return ConditionOutcome.match(ConditionMessage.forCondition(ConditionalOnJsonType.class)
                        .because("api.json.type = " + value));
            }
        }
        return ConditionOutcome.noMatch(ConditionMessage.forCondition(ConditionalOnJsonType.class).because(
                "annotationAttributes is null"));
    }
}
