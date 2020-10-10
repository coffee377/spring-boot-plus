package com.voc.api.response;

import org.springframework.http.HttpStatus;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/23 11:42
 */
public interface IBizError {

    /**
     * 错误编码
     *
     * @return int
     */
    int getCode();

    /**
     * 错误信息
     *
     * @return String
     */
    String getMessage();

    /**
     * 状态码
     *
     * @return HttpStatus
     */
    HttpStatus getStatus();

}
