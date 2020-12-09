package com.voc.api.autoconfigure.json.jackson.deser;

import com.voc.api.autoconfigure.json.JsonProperties;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/28 22:44
 */
@Component
public class LocalDateTimeDeserializerPlus extends BaseTemporalDeserializer<LocalDateTime> {
    public LocalDateTimeDeserializerPlus(JsonProperties jsonProperties) {
        super(jsonProperties);
    }

    @Override
    protected LocalDateTime deserialize4Long(Long value, Type type) {
        return (LocalDateTime) this.getObject(value, type, this.getFormat());
    }

    @Override
    protected LocalDateTime deserialize4String(String value, Type type) {
        return (LocalDateTime) this.getObject(value, type, this.getFormat());
    }
}
