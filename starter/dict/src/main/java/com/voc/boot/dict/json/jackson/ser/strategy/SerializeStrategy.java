package com.voc.boot.dict.json.jackson.ser.strategy;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.voc.common.api.dict.DictionaryItem;

public interface SerializeStrategy {

    boolean support(DictionaryItem dictionaryItem);

    void serialize(DictionaryItem dictItem, JsonGenerator gen, SerializerProvider serializerProvider);
}
