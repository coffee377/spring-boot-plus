package com.voc.api.autoconfigure.json.jackson.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.voc.api.autoconfigure.json.ITemporal;
import com.voc.api.autoconfigure.json.JsonProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/28 10:12
 */
public abstract class BaseTemporalSerializer<T> extends JsonSerializer<T> implements ITemporal {

    private final Class<T> clazz;

    /**
     * 序列化格式
     */
    @Getter
    @Setter
    private String format;

    /**
     * 是否序列化成 UTC 时间戳
     */
    @Getter
    @Setter
    private boolean utcTimestamp;

    @Getter
    private final JsonProperties jsonProperties;

    @SuppressWarnings({"unchecked"})
    public BaseTemporalSerializer(JsonProperties jsonProperties) {
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        this.clazz = (Class<T>) actualTypeArguments[0];
        this.jsonProperties = jsonProperties;
        this.utcTimestamp = jsonProperties.isUtcTimestamp();
        this.format = jsonProperties.getFormat().getLocalDateTime();
    }

    @Override
    public Class<T> handledType() {
        return this.clazz;
    }

    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        Object result;
        if (this.utcTimestamp) {
            result = this.serialize2Timestamp(value, gen, serializers);
        } else {
            result = this.serialize2String(value, gen, serializers);
        }
        gen.writeObject(result);
    }

    /**
     * 序列化成时间戳
     *
     * @param value       T
     * @param gen         JsonGenerator
     * @param serializers SerializerProvider
     * @return Long
     */
    protected abstract Long serialize2Timestamp(T value, JsonGenerator gen, SerializerProvider serializers);

    /**
     * 序列化成字符串
     *
     * @param value       T
     * @param gen         JsonGenerator
     * @param serializers SerializerProvider
     * @return Long
     */
    protected abstract String serialize2String(T value, JsonGenerator gen, SerializerProvider serializers);


}
