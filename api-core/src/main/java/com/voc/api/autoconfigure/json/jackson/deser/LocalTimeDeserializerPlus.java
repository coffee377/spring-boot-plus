package com.voc.api.autoconfigure.json.jackson.deser;

import com.voc.api.autoconfigure.json.JsonProperties;

import java.lang.reflect.Type;
import java.time.LocalTime;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/28 22:45
 */
public class LocalTimeDeserializerPlus extends BaseTemporalDeserializer<LocalTime> {

    public LocalTimeDeserializerPlus(JsonProperties jsonProperties) {
        super(jsonProperties);
    }

    @Override
    protected LocalTime deserialize4Long(Long value, Type type) {
        return (LocalTime) this.getObject(value, type, this.getFormat());
    }

    @Override
    protected LocalTime deserialize4String(String value, Type type) {
        return (LocalTime) this.getObject(value, type, this.getFormat());
    }
}
