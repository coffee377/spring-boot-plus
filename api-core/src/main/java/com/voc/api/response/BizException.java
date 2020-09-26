package com.voc.api.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/03/27 18:52
 */
@Getter
@Setter
public class BizException extends RuntimeException {
    private int code;
    private HttpStatus httpStatus;

    public BizException(String message) {
        this(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, HttpStatus.OK);
    }

    public BizException(IBizError bizError) {
        this(bizError.getCode(), bizError.getMessage(), HttpStatus.OK);
    }

    public BizException(IBizError bizError, HttpStatus httpStatus) {
        this(bizError.getCode(), bizError.getMessage(), httpStatus);
    }

    public BizException(int code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

}
