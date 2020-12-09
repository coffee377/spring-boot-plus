package com.voc.api.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/03/27 18:52
 */
@Getter
@Setter
public class BizException extends RuntimeException {
    private long code;
    private HttpStatus httpStatus;

    public BizException(long code, String message) {
        this(code, message, HttpStatus.OK);
    }

    public BizException(IBizStatus bizStatus) {
        this(bizStatus.getCode(), bizStatus.getMessage(), bizStatus.getStatus());
    }

    public BizException(long code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

}
