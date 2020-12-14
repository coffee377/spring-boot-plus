package com.voc.restful.core.autoconfigure.json;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/24 14:09
 */
@Getter
@Setter
public class JsonFormat {

    /**
     * Instant 类型序列化默认格式
     */
    private String instant = "yyyy-MM-dd HH:mm:ss";

    /**
     * Date 类型序列化默认格式
     */
    private String date = "yyyy-MM-dd HH:mm:ss";

    /**
     * LocalDateTime 类型序列化默认格式
     */
    private String localDateTime = "yyyy-MM-dd HH:mm:ss";

    /**
     * LocalDate 类型序列化默认格式
     */
    private String localDate = "yyyy-MM-dd";

    /**
     * LocalTime 类型序列化默认格式
     */
    private String localTime = "HH:mm:ss";

}
