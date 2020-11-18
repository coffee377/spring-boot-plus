package com.voc.api.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/24 21:16
 * @deprecated ControllerAdvice 只能处理 Controller 层错误，无法处理其他错误，通过自定义 ErrorController 实现
 */
@Slf4j
@Deprecated
public class GlobalControllerAdvice {

    /**
     * 全局异常捕捉处理
     *
     * @param exception Exception
     * @return Error
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result errorHandler(Exception exception, HttpServletResponse response) {
        response.setStatus(HttpStatus.OK.value());
        if (log.isErrorEnabled()) {
            log.error(exception.getMessage(), exception.getCause());
        }
        return Result.failure(exception);
    }

    /**
     * 全局异常捕捉处理
     *
     * @param bizException BizException
     * @return Error
     */
    @ResponseBody
    @ExceptionHandler(BizException.class)
    public Result bizExceptionHandler(BizException bizException, HttpServletResponse response) {
        response.setStatus(bizException.getHttpStatus().value());
        if (log.isErrorEnabled()) {
            log.error(bizException.getMessage(), bizException.getCause());
        }
        return Result.failure(bizException);
    }

}
