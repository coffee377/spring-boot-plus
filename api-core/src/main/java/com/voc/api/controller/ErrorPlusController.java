package com.voc.api.controller;

import com.voc.api.response.BizException;
import com.voc.api.response.Result;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 自定义异常处理
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/30 11:54
 */
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ErrorPlusController extends AbstractErrorController implements ErrorController {

    private final ErrorProperties errorProperties;

    /**
     * Create a new {@link ErrorPlusController} instance.
     *
     * @param errorAttributes  the error attributes
     * @param serverProperties configuration properties
     */
    public ErrorPlusController(ErrorAttributes errorAttributes, ServerProperties serverProperties) {
        super(errorAttributes);
        this.errorProperties = serverProperties.getError();
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Result error(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        response.setStatus(status.value());
        Exception exception = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

        ErrorAttributeOptions options = getErrorAttributeOptions(request, MediaType.ALL);
        Map<String, Object> model = getErrorAttributes(request, options);
//        BizException
        Result result = Result.builder().failure().code(-1).message("AAA").data(model).build();
        return result;
    }

    protected BizException getBizException(HttpServletRequest request) {
        Exception exception = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        if (exception instanceof BizException) {
            return (BizException) exception;
        }
        return null;
    }

    protected Result getErrorCode(HttpStatus status, BizException exception) {
//        Result.builder().
//        Result result = Result.of(-1, "", null);
        Result build = Result.builder().build();
        return null;
    }

    @Override
    public String getErrorPath() {
        return null;
    }

    protected ErrorAttributeOptions getErrorAttributeOptions(HttpServletRequest request, MediaType mediaType) {
        ErrorAttributeOptions options = ErrorAttributeOptions.defaults();
        if (this.errorProperties.isIncludeException()) {
            options = options.including(ErrorAttributeOptions.Include.EXCEPTION);
        }
        if (isIncludeStackTrace(request, mediaType)) {
            options = options.including(ErrorAttributeOptions.Include.STACK_TRACE);
        }
        if (isIncludeMessage(request, mediaType)) {
            options = options.including(ErrorAttributeOptions.Include.MESSAGE);
        }
        if (isIncludeBindingErrors(request, mediaType)) {
            options = options.including(ErrorAttributeOptions.Include.BINDING_ERRORS);
        }
        return options;
    }

    /**
     * Determine if the stacktrace attribute should be included.
     *
     * @param request  the source request
     * @param produces the media type produced (or {@code MediaType.ALL})
     * @return if the stacktrace attribute should be included
     */
    @SuppressWarnings("deprecation")
    protected boolean isIncludeStackTrace(HttpServletRequest request, MediaType produces) {
        switch (this.errorProperties.getIncludeStacktrace()) {
            case ALWAYS:
                return true;
            case ON_PARAM:
            case ON_TRACE_PARAM:
                return getTraceParameter(request);
            default:
                return false;
        }
    }

    /**
     * Determine if the message attribute should be included.
     *
     * @param request  the source request
     * @param produces the media type produced (or {@code MediaType.ALL})
     * @return if the message attribute should be included
     */
    protected boolean isIncludeMessage(HttpServletRequest request, MediaType produces) {
        switch (this.errorProperties.getIncludeMessage()) {
            case ALWAYS:
                return true;
            case ON_PARAM:
                return getMessageParameter(request);
            default:
                return false;
        }
    }

    /**
     * Determine if the errors attribute should be included.
     *
     * @param request  the source request
     * @param produces the media type produced (or {@code MediaType.ALL})
     * @return if the errors attribute should be included
     */
    protected boolean isIncludeBindingErrors(HttpServletRequest request, MediaType produces) {
        switch (this.errorProperties.getIncludeBindingErrors()) {
            case ALWAYS:
                return true;
            case ON_PARAM:
                return getErrorsParameter(request);
            default:
                return false;
        }
    }

}
