package com.voc.restful.core.autoconfigure.json.jackson.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.voc.restful.core.autoconfigure.json.JsonProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/28 11:48
 */
@Component
public class DateSerializerPlus extends BaseTemporalSerializer<Date> {

    public DateSerializerPlus(JsonProperties jsonProperties) {
        super(jsonProperties);
        setFormat(jsonProperties.getFormat().getDate());
    }

    @Override
    protected Long serialize2Timestamp(Date value, JsonGenerator gen, SerializerProvider serializers) {
        return this.serialize2Timestamp(this.getLocalDateTime(value), this.getZoneOffset());
    }

    @Override
    protected String serialize2String(Date value, JsonGenerator gen, SerializerProvider serializers) {
        return this.serialize2String(this.getLocalDateTime(value), this.getFormat());
    }


}
