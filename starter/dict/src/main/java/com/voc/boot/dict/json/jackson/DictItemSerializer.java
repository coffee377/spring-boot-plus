package com.voc.boot.dict.json.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.voc.boot.dict.persist.DataDictItem;
import com.voc.common.api.dict.IDictItem;

import java.io.IOException;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/09 13:46
 */
public class DictItemSerializer extends JsonSerializer<IDictItem> {

    @Override
    public void serialize(IDictItem dictItem, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        AnnotationIntrospector annotationIntrospector = serializerProvider.getAnnotationIntrospector();
        boolean annotationPresent = dictItem.getClass().isAnnotationPresent(DictItemSerialize.class);
        if (!annotationPresent) {
            gen.writeString(dictItem.getText());
            return;
        }
        DictItemSerialize annotation = dictItem.getClass().getAnnotation(DictItemSerialize.class);
        SerializeType type = annotation.type();
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
            gen.writeObjectField("id", ((DataDictItem<?>) dictItem).getId());
        }
        gen.writeObjectField("value", dictItem.getValue());
        gen.writeObjectField("text", dictItem.getText());
        gen.writeObjectField("description", dictItem.getDescription());
        gen.writeEndObject();
    }

}
