package com.voc.restful.core.autoconfigure.json.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.voc.restful.core.autoconfigure.json.JsonProperties;
import com.voc.restful.core.autoconfigure.json.jackson.deser.BaseTemporalDeserializer;
import com.voc.restful.core.autoconfigure.json.jackson.ser.BaseTemporalSerializer;
import com.voc.restful.core.autoconfigure.json.jackson.ser.ResultSerializer;
import com.voc.restful.core.response.Result;
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

        /* 注册转换器 */
        SimpleModule resultModule = new SimpleModule();
        ResultSerializer resultSerializer = applicationContext.getBean(ResultSerializer.class);
        resultModule.addSerializer(Result.class, resultSerializer);
        builder.modules(resultModule);

        configureSerializer(builder);

        configureDeserializer(builder);

    }

    private void configureSerializer(Jackson2ObjectMapperBuilder builder) {
        /* 时间序列化 */
        List<JsonSerializer> serializers = new ArrayList<>(applicationContext.getBeansOfType(BaseTemporalSerializer.class).values());
        builder.serializers(serializers.toArray(new JsonSerializer[0]));
    }

    private void configureDeserializer(Jackson2ObjectMapperBuilder builder) {
        /* 时间反序列化 */
        List<JsonDeserializer> deserializers = new ArrayList<>(applicationContext.getBeansOfType(BaseTemporalDeserializer.class).values());
        builder.deserializers(deserializers.toArray(new JsonDeserializer[0]));
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
