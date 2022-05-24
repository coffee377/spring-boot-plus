package com.voc.common;

import com.voc.common.exception.BizException;
import com.voc.restful.core.response.impl.InternalBizStatus;
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
     * 是否内部错误编码
     *
     * @return false
     */
    default boolean internal() {
        return false;
    }

    /**
     * 模块编码
     *
     * @return int
     */
     int getModule();

    /**
     * 错误码唯一标识
     *
     * @return int
     */
    int getMask();

    /**
     * 业务状态编码
     *
     * @return Long
     */
    default long getCode() {
        int one = 0;
        if (!internal()) {
            HttpStatus status = getStatus();
            if (status.value() >= HttpStatus.BAD_REQUEST.value() && status.value() < HttpStatus.INSUFFICIENT_STORAGE.value()) {
                one = 4;
            } else if (status.value() >= HttpStatus.INSUFFICIENT_STORAGE.value()) {
                one = 5;
            }
        }
        int two = getModule();
        if (two < 0 || two > 999) {
            throw new BizException(InternalBizStatus.OUT_OF_MODULE_CODE_RANGE, two, "1~999");
        }
        int three = getMask();
        if (three < 0 || three > 99999) {
            throw new BizException(InternalBizStatus.OUT_OF_ERROR_CODE_RANGE, three, "1~99999");
        }
        String code = String.format("%s%s%s", String.format("%01d", one), String.format("%03d", two), String.format("%05d", three));
        return Long.parseLong(code);
    }

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
