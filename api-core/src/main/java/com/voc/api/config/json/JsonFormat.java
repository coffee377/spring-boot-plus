package com.voc.api.config.json;

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

    private String date = "yyyy-MM-dd HH:mm:ss";

    private String localDateTime = "yyyy-MM-dd HH:mm:ss";

    private String localDate = "yyyy-MM-dd";

    private String localTime = "HH:mm:ss";

}
