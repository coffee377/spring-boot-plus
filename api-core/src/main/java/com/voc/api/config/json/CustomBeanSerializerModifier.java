package com.voc.api.config.json;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/24 12:14
 */
@Getter
@Setter
public class CustomBeanSerializerModifier extends BeanSerializerModifier {

    private JsonSerializer<Object> dateJsonSerializer;

    public CustomBeanSerializerModifier(JsonSerializer<Object> dateJsonSerializer) {
        this.dateJsonSerializer = dateJsonSerializer;
    }

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
        for (BeanPropertyWriter writer : beanProperties) {
            if (isDate(writer)) {
                writer.assignSerializer(dateJsonSerializer);
            }
        }
        return beanProperties;
    }

    /**
     * 判断日期类型
     *
     * @param writer BeanPropertyWriter
     * @return boolean
     */
    boolean isDate(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return clazz.equals(Date.class) || clazz.equals(LocalDateTime.class)
                || clazz.equals(LocalDate.class) || clazz.equals(LocalTime.class);
    }
}
