package com.voc.api.autoconfigure.json.jackson.deser;

import com.voc.api.autoconfigure.json.JsonProperties;

import java.lang.reflect.Type;
import java.time.Instant;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/28 22:43
 */
public class InstantDeserializerPlus extends BaseTemporalDeserializer<Instant> {

    public InstantDeserializerPlus(JsonProperties jsonProperties) {
        super(jsonProperties);
    }

    @Override
    protected Instant deserialize4Long(Long value, Type type) {
        return (Instant) this.getObject(value, type, this.getFormat());
    }

    @Override
    protected Instant deserialize4String(String value, Type type) {
        return (Instant) this.getObject(value, type, this.getFormat());
    }
}
