package com.voc.api.autoconfigure.json.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.voc.api.autoconfigure.json.JsonProperties;
import com.voc.api.autoconfigure.json.jackson.deser.BaseTemporalDeserializer;
import com.voc.api.autoconfigure.json.jackson.ser.BaseTemporalSerializer;
import lombok.Getter;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/27 09:07
 */
@Getter
public class Jackson2ObjectMapperBuilderPlus implements Jackson2ObjectMapperBuilderCustomizer, Ordered {

    private final ApplicationContext applicationContext;

    private final JsonProperties jsonProperties;

    private final JacksonProperties jacksonProperties;

    public Jackson2ObjectMapperBuilderPlus(ApplicationContext applicationContext,
                                           JsonProperties jsonProperties,
                                           JacksonProperties jacksonProperties) {
        this.applicationContext = applicationContext;
        this.jsonProperties = jsonProperties;
        this.jacksonProperties = jacksonProperties;
    }

    @Override
    public void customize(Jackson2ObjectMapperBuilder builder) {
        builder.featuresToEnable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);

        if (this.jacksonProperties.getDefaultPropertyInclusion() != null) {
            builder.serializationInclusion(this.jacksonProperties.getDefaultPropertyInclusion());
        } else {
            /* 不序列化值为空的属性(包括空对象、空字符串、集合长度为0) */
            builder.serializationInclusion(JsonInclude.Include.NON_EMPTY);
        }
        if (this.jacksonProperties.getTimeZone() != null) {
            builder.timeZone(this.jacksonProperties.getTimeZone());
        } else {
            builder.timeZone(TimeZone.getDefault());
        }

        configureTemporalSerializer(builder);

        configureTemporalDeserializer(builder);

    }

    private void configureTemporalSerializer(Jackson2ObjectMapperBuilder builder) {
        List<BaseTemporalSerializer> temporalSerializers = new ArrayList<>(applicationContext.getBeansOfType(BaseTemporalSerializer.class).values());
        builder.serializers(temporalSerializers.toArray(new JsonSerializer[0]));
    }

    private void configureTemporalDeserializer(Jackson2ObjectMapperBuilder builder) {
        List<BaseTemporalDeserializer> temporalDeserializers = new ArrayList<>(applicationContext.getBeansOfType(BaseTemporalDeserializer.class).values());
        builder.deserializers(temporalDeserializers.toArray(new JsonDeserializer[0]));
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
