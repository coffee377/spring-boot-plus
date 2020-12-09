package com.voc.api.response;

import org.springframework.http.HttpStatus;

/**
 * 业务状态接口
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/23 11:42
 */
public interface IBizStatus {

    /**
     * 业务状态编码
     *
     * @return Long
     */
    long getCode();

    /**
     * 业务状态信息
     *
     * @return String
     */
    String getMessage();

    /**
     * Http 状态码
     *
     * @return HttpStatus
     */
    HttpStatus getStatus();

}
