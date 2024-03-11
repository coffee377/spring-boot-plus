package com.voc.boot.dict.json.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.voc.boot.dict.json.annotation.DictSerialize;
import com.voc.boot.dict.json.jackson.ser.DictItemContextHolder;
import com.voc.boot.dict.persist.DataDictItem;
import com.voc.common.api.dict.DictionaryItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/09 13:46
 */
@Slf4j
public class DictItemSerializer extends JsonSerializer<DictionaryItem> implements ContextualSerializer {

    private final DictItemContextHolder context;
    private final DictSerializeProperties serializeProperties;

    public DictItemSerializer(DictSerializeProperties serializeProperties) {
        this(serializeProperties, new DictItemContextHolder(serializeProperties));
    }

    public DictItemSerializer(DictSerializeProperties serializeProperties, DictItemContextHolder context) {
        this.serializeProperties = serializeProperties;
        this.context = context;
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty beanProperty) throws JsonMappingException {
        if (!ObjectUtils.isEmpty(beanProperty)) {
            Class<?> rawClass = beanProperty.getType().getRawClass();
            if (DictionaryItem.class.isAssignableFrom(rawClass)) {
                DictSerialize annotation1 = null, annotation2 = null;
                /* 获取实现类上的注解 */
                if (rawClass.isAnnotationPresent(DictSerialize.class)) {
                    annotation1 = AnnotationUtils.getAnnotation(rawClass, DictSerialize.class);
                }

                /* 获取属性上的注解 */
                if (beanProperty.getMember().hasAnnotation(DictSerialize.class)) {
                    annotation2 = beanProperty.getAnnotation(DictSerialize.class);
                    if (annotation2 == null) {
                        annotation2 = beanProperty.getContextAnnotation(DictSerialize.class);
                    }

                    if (annotation2 != null) {
                        annotation2 = AnnotationUtils.synthesizeAnnotation(annotation2, DictSerialize.class);
                    }
                }

                DictItemContextHolder contextHolder = new DictItemContextHolder(serializeProperties, annotation1, annotation2);
                return new DictItemSerializer(serializeProperties, contextHolder);

            }
        }
        return this;
    }

    @Override
    public void serialize(DictionaryItem dictItem, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        if (context.isWriteMultipleFields()) {
            serializeMultiple(dictItem, gen, context);
        } else {
            serializeSingle(dictItem, gen, context);
        }
    }


    @Override
    public Class<DictionaryItem> handledType() {
        return DictionaryItem.class;
    }

    /**
     * 序列化单个字段
     *
     * @param dictItem 数据字典
     * @param gen      JsonGenerator
     * @param context  自定义序列化上下文
     * @throws IOException IO异常
     */
    private void serializeSingle(DictionaryItem<?> dictItem, JsonGenerator gen, DictItemContextHolder context) throws IOException {
        if (context.isWriteMultipleFields()) return;
        if (context.canWriteId()) {
            if (DataDictItem.class.isAssignableFrom(dictItem.getClass())) {
                gen.writeObject(((DataDictItem<?>) dictItem).getId());
            } else {
                gen.writeObject(null);
            }
            return;
        }
        if (context.canWriteLabel()) {
            gen.writeString(dictItem.getLabel());
            return;
        }
        if (context.canWriteValue()) {
            gen.writeObject(dictItem.getValue());
            return;
        }
        if (context.canWriteCode()) {
            gen.writeString(dictItem.getCode());
            return;
        }
        if (context.canWriteDescription()) {
            gen.writeString(dictItem.getDescription());
        }

    }

    /**
     * 序列化多个字段
     *
     * @param dictItem 数据字典
     * @param gen      JsonGenerator
     * @param context  自定义序列化上下文
     * @throws IOException IO异常
     */
    private void serializeMultiple(DictionaryItem<?> dictItem, JsonGenerator gen, DictItemContextHolder context) throws IOException {
        if (!context.isWriteMultipleFields()) return;
        gen.writeStartObject();
        if (DataDictItem.class.isAssignableFrom(dictItem.getClass()) && context.canWriteId()) {
            gen.writeObjectField(context.getId(), ((DataDictItem<?>) dictItem).getId());
        }
        if (context.canWriteLabel()) {
            gen.writeObjectField(context.getLabel(), dictItem.getLabel());
        }
        if (context.canWriteValue()) {
            gen.writeObjectField(context.getValue(), dictItem.getValue());
        }
        if (context.canWriteCode()) {
            gen.writeObjectField("code", dictItem.getCode());
        }
        if (context.canWriteDescription()) {
            gen.writeObjectField(context.getDescription(), dictItem.getDescription());
        }
        gen.writeEndObject();
    }

}
