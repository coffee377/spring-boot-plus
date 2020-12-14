package com.voc.restful.core.autoconfigure.json;

import java.lang.reflect.Type;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/28 10:37
 */
public interface ITemporal extends IZoneInfo {

    LocalDate DEFAULT_INIT_LOCAL_DATE = LocalDate.of(1970, 1, 1);

    /**
     * 本地时间转字符串
     *
     * @param localDateTime LocalDateTime
     * @param format        时间格式
     * @return String
     */
    default String serialize2String(LocalDateTime localDateTime, String format) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 本地时间转字 UTC 时间戳
     *
     * @param localDateTime LocalDateTime
     * @param zoneOffset    ZoneOffset
     * @return Long
     */
    default Long serialize2Timestamp(LocalDateTime localDateTime, ZoneOffset zoneOffset) {
        if (localDateTime == null) {
            return null;
        }
        return OffsetDateTime.of(localDateTime, zoneOffset).toInstant().toEpochMilli();
    }

    /**
     * 获取 LocalDateTime
     *
     * @param value Object
     * @return LocalDateTime
     */
    default LocalDateTime getLocalDateTime(Object value) {
        if (value instanceof Instant) {
            return LocalDateTime.ofInstant((Instant) value, this.getZoneId());
        } else if (value instanceof Date) {
            return LocalDateTime.ofInstant(((Date) value).toInstant(), this.getZoneId());
        } else if (value instanceof LocalDateTime) {
            return (LocalDateTime) value;
        } else if (value instanceof LocalDate) {
            return LocalDateTime.of((LocalDate) value, LocalTime.MIN);
        } else if (value instanceof LocalTime) {
            return LocalDateTime.of(DEFAULT_INIT_LOCAL_DATE, (LocalTime) value);
        }
        return null;
    }


    /**
     * 获取 UTC 时间戳
     *
     * @param value      Object
     * @param type       String
     * @param jsonFormat JsonFormat
     * @return Object
     */
    default Object getObject(Object value, Type type, JsonFormat jsonFormat) {
        if (Instant.class.getTypeName().equals(type.getTypeName())) {
            if (value instanceof Long) {
                return Instant.ofEpochMilli((Long) value);
            }
            if (value instanceof String) {
                return LocalDateTime.parse((String) value, DateTimeFormatter.ofPattern(jsonFormat.getInstant())).toInstant(this.getZoneOffset());
            }
        }

        if (Date.class.getTypeName().equals(type.getTypeName())) {
            if (value instanceof Long) {
                return new Date((Long) value);
            }
            if (value instanceof String) {
                LocalDateTime localDateTime = LocalDateTime.parse((String) value,
                        DateTimeFormatter.ofPattern(jsonFormat.getDate()));
                return Date.from(localDateTime.toInstant(this.getZoneOffset()));
            }
        }

        if (LocalDateTime.class.getTypeName().equals(type.getTypeName())) {
            if (value instanceof Long) {
                Instant instant = Instant.ofEpochMilli((Long) value);
                return LocalDateTime.ofInstant(instant, this.getZoneId());
            }
            if (value instanceof String) {
                return LocalDateTime.parse((String) value, DateTimeFormatter.ofPattern(jsonFormat.getLocalDateTime()));
            }
        }

        if (LocalDate.class.getTypeName().equals(type.getTypeName())) {
            if (value instanceof Long) {
                Instant instant = Instant.ofEpochMilli((Long) value);
                return LocalDateTime.ofInstant(instant, this.getZoneId()).toLocalDate();
            }
            if (value instanceof String) {
                return LocalDate.parse((String) value, DateTimeFormatter.ofPattern(jsonFormat.getLocalDate()));
            }
        }

        if (LocalTime.class.getTypeName().equals(type.getTypeName())) {
            if (value instanceof Long) {
                Instant instant = Instant.ofEpochMilli((Long) value);
                return LocalDateTime.ofInstant(instant, this.getZoneId()).toLocalTime();
            }
            if (value instanceof String) {
                return LocalTime.parse((String) value, DateTimeFormatter.ofPattern(jsonFormat.getLocalTime()));
            }
        }

        return null;
    }

}
