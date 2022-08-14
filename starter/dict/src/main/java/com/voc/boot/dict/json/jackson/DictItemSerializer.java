package com.voc.boot.dict.json.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.voc.boot.dict.persist.DataDictItem;
import com.voc.common.api.dict.IDictItem;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/09 13:46
 */
public class DictItemSerializer extends JsonSerializer<IDictItem>  {

    private final DictItemSerializeProperties serializeProperties;

    public DictItemSerializer(DictItemSerializeProperties serializeProperties) {
        this.serializeProperties = serializeProperties;
    }

    private SerializeType serializeType;


    public void setSerializeType(SerializeType serializeType) {
        this.serializeType = serializeType;
    }

    public SerializeType getSerializeType() {
        return serializeType;
    }

//    @Override
//    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
//        DictItemSerializer dictItemSerializer = new DictItemSerializer(serializeProperties);
//
//        if (!ObjectUtils.isEmpty(beanProperty)) {
//            DictItemSerialize annotation = beanProperty.getAnnotation(DictItemSerialize.class);
//            SerializeType type = getSerializeType(annotation);
//            dictItemSerializer.setSerializeType(type);
//        }
//
//        return dictItemSerializer;
//    }

    @Override
    public void serialize(IDictItem dictItem, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        SerializeType type = mergeSerializeType(dictItem);

        switch (type) {
            case TEXT:
                gen.writeString(dictItem.getText());
                break;
            case VALUE:
                gen.writeObject(dictItem.getValue());
                break;
            case DESCRIPTION:
                gen.writeString(dictItem.getDescription());
                break;
            case OBJECT:
            default:
                serializeObject(dictItem, gen);
        }
    }


    @Override
    public Class<IDictItem> handledType() {
        return IDictItem.class;
    }

    private void serializeObject(IDictItem<?> dictItem, JsonGenerator gen) throws IOException {
        gen.writeStartObject();
        if (DataDictItem.class.isAssignableFrom(dictItem.getClass())) {
            gen.writeObjectField(serializeProperties.getId(), ((DataDictItem<?>) dictItem).getId());
        }
        gen.writeObjectField(serializeProperties.getValue(), dictItem.getValue());
        gen.writeObjectField(serializeProperties.getText(), dictItem.getText());
        gen.writeObjectField(serializeProperties.getDescription(), dictItem.getDescription());
        gen.writeEndObject();
    }

    private SerializeType forIDictItemImpl(IDictItem dictItem) {
        DictItemSerialize annotation = dictItem.getClass().getAnnotation(DictItemSerialize.class);
        return getSerializeType(annotation);
    }

    private SerializeType getSerializeType(DictItemSerialize annotation) {
        if (!ObjectUtils.isEmpty(annotation)) {
            DictItemSerialize dictItemAnnotation = AnnotationUtils.synthesizeAnnotation(annotation, DictItemSerialize.class);
            if (!ObjectUtils.isEmpty(dictItemAnnotation)) {
                return dictItemAnnotation.type();
            }
        }
        return null;
    }

    /**
     * 按照注解位置优先级获取序列化类型
     *
     * @param dictItem 数据字典项实现类
     * @return SerializeType
     */
    private SerializeType mergeSerializeType(IDictItem dictItem) {
        /* 1. 实体属性字段上的注解优先 */
        if (serializeType != null) {
            return serializeType;
        }

        /* 2. IDictItem 实现类上注解其次 */
        SerializeType global = forIDictItemImpl(dictItem);
        if (global != null) {
            return global;
        }

        /* 3. 找不到则使用默认 */
        return serializeProperties.type;
    }

}
