package com.voc.restful.core.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/03/26 22:47
 */
@Data
@AllArgsConstructor
public class ErrorMapping {
    /**
     * 错误编码
     */
    private long code;
    /**
     * 错误信息
     */
    private String message;
    /**
     * http 状态码
     */
    private int status;
}
