package com.voc.common.api.biz;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/03/27 18:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BizException extends RuntimeException {
    private long code;
    private int httpStatus;

    /**
     * @param code       错误编码
     * @param message    错误信息
     * @param httpStatus HTTP 状态码
     * @param formatArgs 信息格式化参数
     */
    public BizException(long code, String message, int httpStatus, Object... formatArgs) {
        super(String.format(message, formatArgs));
        this.code = code;
        this.httpStatus = httpStatus;
    }

    /**
     * @param code       错误编码
     * @param message    错误信息
     * @param formatArgs 信息格式化参数
     */
    public BizException(long code, String message, Object... formatArgs) {
        this(code, message, 500, formatArgs);
    }

    /**
     * @param bizStatus  业务状态
     * @param formatArgs 信息格式化参数
     */
    public BizException(IBizStatus bizStatus, Object... formatArgs) {
        this(bizStatus.getCode(), bizStatus.getMessage(), bizStatus.getHttpStatus(), formatArgs);
    }
}
