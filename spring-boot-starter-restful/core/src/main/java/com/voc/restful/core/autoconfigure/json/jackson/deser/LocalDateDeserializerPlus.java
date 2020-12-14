package com.voc.restful.core.autoconfigure.json.jackson.deser;

import com.voc.restful.core.autoconfigure.json.JsonProperties;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.time.LocalDate;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/28 22:43
 */
@Component
public class LocalDateDeserializerPlus extends BaseTemporalDeserializer<LocalDate> {
    public LocalDateDeserializerPlus(JsonProperties jsonProperties) {
        super(jsonProperties);
    }

    @Override
    protected LocalDate deserialize4Long(Long value, Type type) {
        return (LocalDate) this.getObject(value, type, this.getFormat());
    }

    @Override
    protected LocalDate deserialize4String(String value, Type type) {
        return (LocalDate) this.getObject(value, type, this.getFormat());
    }

}
