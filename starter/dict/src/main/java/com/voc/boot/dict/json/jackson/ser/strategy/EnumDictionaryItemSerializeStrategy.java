package com.voc.boot.dict.json.jackson.ser.strategy;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.voc.common.api.dict.DictionaryItem;
import com.voc.common.api.dict.EnumDictItem;

public class EnumDictionaryItemSerializeStrategy implements SerializeStrategy {
    @Override
    public boolean support(DictionaryItem dictionaryItem) {
        return EnumDictItem.class.isAssignableFrom(dictionaryItem.getClass());
    }

    @Override
    public void serialize(DictionaryItem dictItem, JsonGenerator gen, SerializerProvider serializerProvider) {

    }
}
