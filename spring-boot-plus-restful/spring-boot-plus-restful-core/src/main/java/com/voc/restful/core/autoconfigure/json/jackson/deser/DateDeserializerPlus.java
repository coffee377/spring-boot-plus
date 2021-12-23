package com.voc.restful.core.autoconfigure.json.jackson.deser;

import com.voc.restful.core.autoconfigure.json.JsonProperties;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/28 10:16
 */
@Component
public class DateDeserializerPlus extends BaseTemporalDeserializer<Date> {

    public DateDeserializerPlus(JsonProperties jsonProperties) {
        super(jsonProperties);
    }

    @Override
    protected Date deserialize4Long(Long value, Type type) {
        return (Date) this.getObject(value, type, this.getFormat());
    }

    @Override
    protected Date deserialize4String(String value, Type type) {
        return (Date) this.getObject(value, type, this.getFormat());
    }
}
