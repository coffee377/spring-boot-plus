package com.voc.restful.core.autoconfigure.json.jackson.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.voc.restful.core.autoconfigure.json.JsonProperties;
import com.voc.restful.core.autoconfigure.json.annotation.Temporal;

import java.time.LocalDate;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/28 09:26
 */
@Temporal
public class LocalDateSerializerPlus extends BaseTemporalSerializer<LocalDate> {

    public LocalDateSerializerPlus(JsonProperties jsonProperties) {
        super(jsonProperties);
        setFormat(jsonProperties.getFormat().getLocalDate());
    }

    @Override
    protected Long serialize2Timestamp(LocalDate value, JsonGenerator gen, SerializerProvider serializers) {
        return this.serialize2Timestamp(this.getLocalDateTime(value), this.getZoneOffset());
    }

    @Override
    protected String serialize2String(LocalDate value, JsonGenerator gen, SerializerProvider serializers) {
        return this.serialize2String(this.getLocalDateTime(value), this.getFormat());
    }

}
