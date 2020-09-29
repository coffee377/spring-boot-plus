package com.voc.api.autoconfigure.json.jackson;

import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.voc.api.autoconfigure.json.jackson.deser.BaseTemporalDeserializer;
import com.voc.api.autoconfigure.json.jackson.ser.BaseTemporalSerializer;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/29 11:51
 */
public class JavaTimeModulePlus extends SimpleModule implements Ordered {

    private final List<BaseTemporalSerializer> serializers;
    private final List<BaseTemporalDeserializer> deserializers;

    public JavaTimeModulePlus(List<BaseTemporalSerializer> serializers, List<BaseTemporalDeserializer> deserializers) {
        super(VersionUtil.parseVersion("0.0.1", "com.voc.api", "api-json"));
        this.serializers = serializers;
        this.deserializers = deserializers;
    }

    @Override
    public void setupModule(SetupContext context) {
        super.setupModule(context);
        Optional.ofNullable(serializers).ifPresent(items -> items.forEach(this::addJsonSerializerBean));
        Optional.ofNullable(deserializers).ifPresent(items -> items.forEach(this::addJsonDeserializerBean));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    @SuppressWarnings("unchecked")
    protected <T> void addJsonSerializerBean(JsonSerializer<T> serializer, Class<?>... types) {
        Class<T> baseType = (Class<T>) ResolvableType.forClass(JsonSerializer.class, serializer.getClass()).resolveGeneric();
        addBeanToModule(serializer, baseType, types, this::addSerializer);
    }

    @SuppressWarnings("unchecked")
    protected <T> void addJsonDeserializerBean(JsonDeserializer<T> deserializer, Class<?>... types) {
        Class<T> baseType = (Class<T>) ResolvableType.forClass(JsonDeserializer.class, deserializer.getClass()).resolveGeneric();
        addBeanToModule(deserializer, baseType, types, this::addDeserializer);
    }

    @SuppressWarnings("unchecked")
    private <E, T> void addBeanToModule(E element, Class<T> baseType, Class<?>[] types, BiConsumer<Class<T>, E> consumer) {
        if (ObjectUtils.isEmpty(types)) {
            consumer.accept(baseType, element);
            return;
        }
        for (Class<?> type : types) {
            Assert.isAssignable(baseType, type);
            consumer.accept((Class<T>) type, element);
        }
    }

}
