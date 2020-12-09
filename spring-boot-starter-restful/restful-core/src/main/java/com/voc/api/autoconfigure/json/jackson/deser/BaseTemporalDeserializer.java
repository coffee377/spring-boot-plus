package com.voc.api.autoconfigure.json.jackson.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.voc.api.autoconfigure.json.ITemporal;
import com.voc.api.autoconfigure.json.JsonFormat;
import com.voc.api.autoconfigure.json.JsonProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/28 10:16
 */
public abstract class BaseTemporalDeserializer<T> extends JsonDeserializer<T> implements ITemporal {
    /**
     * 序列化格式
     */
    @Getter
    @Setter
    private JsonFormat format;

    private final Class<T> clazz;

    @SuppressWarnings({"unchecked"})
    public BaseTemporalDeserializer(JsonProperties jsonProperties) {
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        this.clazz = (Class<T>) actualTypeArguments[0];
        this.format = jsonProperties.getFormat();
    }

    @Override
    public Class<?> handledType() {
        return this.clazz;
    }

    @Override
    public T deserialize(JsonParser p, DeserializationContext context) throws IOException, JsonProcessingException {
        JsonNode rootNode = p.getCodec().readTree(p);
        Type type = this.handledType();
        if (rootNode instanceof NumericNode) {
            return this.deserialize4Long(rootNode.asLong(), type);
        } else if (rootNode instanceof TextNode) {
            return this.deserialize4String(rootNode.asText(), type);
        }
        return null;
    }

    /**
     * 反序列化时间戳
     *
     * @param value Long
     * @param type  Type
     * @return T
     */
    protected abstract T deserialize4Long(Long value, Type type);

    /**
     * 反序列化字符串
     *
     * @param value String
     * @param type  Type
     * @return T
     */
    protected abstract T deserialize4String(String value, Type type);

}
