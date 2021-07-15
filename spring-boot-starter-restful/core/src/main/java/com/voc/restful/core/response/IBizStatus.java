package com.voc.restful.core.response;

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
     * 模块编码
     *
     * @return int
     */
    default int getModule() {
        return 1;
    }

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
     * 重置错误信息
     *
     * @param message 提示信息
     * @return IBizStatus
     */
    default IBizStatus message(String message) {
        return this;
    }

    /**
     * Http 状态码
     *
     * @return HttpStatus
     */
    HttpStatus getStatus();

    /**
     * Http 状态码值
     *
     * @return int
     */
    default int getHttpStatus() {
        return this.getStatus().value();
    }

}
