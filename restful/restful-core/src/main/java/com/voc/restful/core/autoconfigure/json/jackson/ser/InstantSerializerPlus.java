package com.voc.restful.core.autoconfigure.json.jackson.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.voc.restful.core.autoconfigure.json.JsonProperties;
import com.voc.restful.core.autoconfigure.json.annotation.Temporal;

import java.time.Instant;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/28 11:37
 */
@Temporal
public class InstantSerializerPlus extends BaseTemporalSerializer<Instant> {

    public InstantSerializerPlus(JsonProperties jsonProperties) {
        super(jsonProperties);
        setFormat(jsonProperties.getFormat().getInstant());
    }

    @Override
    protected Long serialize2Timestamp(Instant value, JsonGenerator gen, SerializerProvider serializers) {
        return this.serialize2Timestamp(this.getLocalDateTime(value), this.getZoneOffset());
    }

    @Override
    protected String serialize2String(Instant value, JsonGenerator gen, SerializerProvider serializers) {
        return this.serialize2String(this.getLocalDateTime(value), this.getFormat());
    }
}
