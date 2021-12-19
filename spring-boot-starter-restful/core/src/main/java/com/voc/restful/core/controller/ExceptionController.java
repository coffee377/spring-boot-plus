package com.voc.restful.core.controller;

import com.voc.restful.core.response.BizException;
import com.voc.restful.core.response.IBizStatus;
import com.voc.restful.core.response.Result;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.NestedServletException;

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
@Slf4j
@RestController
@RequestMapping("${server.error.path:${error.path:/error}}")
@ConditionalOnProperty(prefix = "api.json", name = "exception-result", havingValue = "json", matchIfMissing = true)
public class ExceptionController extends AbstractErrorController implements ErrorController {

    private final ErrorProperties errorProperties;

    /**
     * Create a new {@link ExceptionController} instance.
     *
     * @param errorAttributes  the error attributes
     * @param serverProperties configuration properties
     */
    public ExceptionController(ErrorAttributes errorAttributes, ServerProperties serverProperties) {
        super(errorAttributes);
        this.errorProperties = serverProperties.getError();
    }

    @ResponseBody
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Result error(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        response.setStatus(status.value());
        ErrorAttributeOptions options = getErrorAttributeOptions(request, MediaType.ALL);
        Map<String, Object> model = getErrorAttributes(request, options);
        Exception exception = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        ExceptionData exceptionData = new ExceptionData(exception, model);
        model.put("status", exceptionData.getStatus().value());
        model.put("error", exceptionData.getStatus().toString());
        model.put("message", exceptionData.getMessage());
        return Result.builder().failure(exceptionData).data(model).build();
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


    @Getter
    static class ExceptionData implements IBizStatus {

        private final Exception exception;
        private final Map<String, Object> model;

        public ExceptionData(Exception exception, Map<String, Object> model) {
            this.exception = exception;
            this.model = model;
        }

        @Override
        public long getCode() {
            if (exception instanceof NestedServletException) {
                Throwable cause = exception.getCause();
                if (cause instanceof BizException) {
                    return ((BizException) cause).getCode();
                }
            } else if (exception instanceof BizException) {
                return ((BizException) exception).getCode();
            }

            return getStatus().value();
        }

        @Override
        public String getMessage() {
            if (exception instanceof NestedServletException) {
                Throwable cause = exception.getCause();
                if (cause instanceof BizException) {
                    return cause.getMessage();
                }
            } else if (exception instanceof BizException) {
                return exception.getMessage();
            }

            String error = model.get("error").toString();
            if (StringUtils.hasLength(error)) {
                return error;
            }
            return model.get("message").toString();
        }

        @Override
        public HttpStatus getStatus() {
            if (exception instanceof NestedServletException) {
                Throwable cause = exception.getCause();
                if (cause instanceof BizException) {
                    return ((BizException) cause).getHttpStatus();
                }
            } else if (exception instanceof BizException) {
                return ((BizException) exception).getHttpStatus();
            }
            try {
                return HttpStatus.valueOf(Integer.parseInt(model.get("status").toString()));
            } catch (NumberFormatException e) {
                log.error(e.getMessage());
            }
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

    }

}
