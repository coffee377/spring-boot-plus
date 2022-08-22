package com.voc.boot.dict.json.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.voc.boot.dict.persist.DataDictItem;
import com.voc.common.api.dict.IDictItem;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/09 13:46
 */
@JsonComponent
public class DictItemSerializer extends JsonSerializer<IDictItem> implements ContextualSerializer {

    private final DictItemSerializeProperties serializeProperties;

    public DictItemSerializer(DictItemSerializeProperties serializeProperties) {
        this.serializeProperties = serializeProperties;
    }

    private List<SerializeType> serializeType;


    @Override
    public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property) throws JsonMappingException {
//        DictItemSerializer dictItemSerializer = new DictItemSerializer(serializeProperties);

        if (!ObjectUtils.isEmpty(property)) {
            DictItemSerialize annotation = property.getAnnotation(DictItemSerialize.class);
            this.serializeType = getSerializeType(annotation);
        }
        return this;
//        return provider.findValueSerializer(property.getType());
    }

    @Override
    public void serialize(IDictItem dictItem, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        List<SerializeType> types = mergeSerializeType(dictItem);
        serializeObject(dictItem, gen, types);

        /* 序列化所有属性 */
        boolean all = types.stream().anyMatch(Predicate.isEqual(SerializeType.ALL));

        /* 序列化单个属性 */
        boolean single = !all && types.size() == 1;

        /* 序列化多个值 */

//        if (all) {
//            serializeObject(dictItem, gen);
////        switch (type) {
////            case TEXT:
////                gen.writeString(dictItem.getText());
////                break;
////            case VALUE:
////                gen.writeObject(dictItem.getValue());
////                break;
////            case DESCRIPTION:
////                gen.writeString(dictItem.getDescription());
////                break;
////            case ALL:
////            default:
//
//        } else {
//
//        }
////        }
    }


    @Override
    public Class<IDictItem> handledType() {
        return IDictItem.class;
    }

    private void serializeObject(IDictItem<?> dictItem, JsonGenerator gen) throws IOException {
//        gen.writeStartObject();
//        if (DataDictItem.class.isAssignableFrom(dictItem.getClass())) {
//            gen.writeObjectField(serializeProperties.getId(), ((DataDictItem<?>) dictItem).getId());
//        }
//        gen.writeObjectField(serializeProperties.getValue(), dictItem.getValue());
//        gen.writeObjectField(serializeProperties.getText(), dictItem.getText());
//        gen.writeObjectField(serializeProperties.getDescription(), dictItem.getDescription());
//        gen.writeEndObject();
    }

    private void serializeObject(IDictItem<?> dictItem, JsonGenerator gen, List<SerializeType> serializeTypes) throws IOException {
        gen.writeStartObject();
        if (DataDictItem.class.isAssignableFrom(dictItem.getClass())) {
            gen.writeObjectField(serializeProperties.getId(), ((DataDictItem<?>) dictItem).getId());
        }
        gen.writeObjectField(serializeProperties.getValue(), dictItem.getValue());
        gen.writeObjectField(serializeProperties.getText(), dictItem.getText());
        gen.writeObjectField(serializeProperties.getDescription(), dictItem.getDescription());
        gen.writeEndObject();
    }

    private List<SerializeType> forIDictItemImpl(IDictItem dictItem) {
        DictItemSerialize annotation = dictItem.getClass().getAnnotation(DictItemSerialize.class);
        return getSerializeType(annotation);
    }

    private List<SerializeType> getSerializeType(DictItemSerialize annotation) {
        if (!ObjectUtils.isEmpty(annotation)) {
            DictItemSerialize dictItemAnnotation = AnnotationUtils.synthesizeAnnotation(annotation, DictItemSerialize.class);
            if (!ObjectUtils.isEmpty(dictItemAnnotation)) {
                return Arrays.asList(dictItemAnnotation.type());
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
    private List<SerializeType> mergeSerializeType(IDictItem dictItem) {
        /* 1. 实体属性字段上的注解优先 */
        if (serializeType != null) {
            return serializeType;
        }

        /* 2. IDictItem 实现类上注解其次 */
        List<SerializeType> global = forIDictItemImpl(dictItem);
        if (global != null) {
            return global;
        }

        /* 3. 找不到则使用默认 */
        return serializeProperties.type;
    }

}
