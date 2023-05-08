package com.voc.boot.result.response.impl;

import com.voc.boot.result.Result;
import com.voc.boot.result.properties.ResultProperties;
import com.voc.boot.result.properties.ResultWrapper;
import com.voc.boot.result.response.ResponseHandler;
import com.voc.common.api.biz.IBizStatus;
import com.voc.common.api.biz.InternalBizStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/25 09:34
 */
@Slf4j
public class ResultResponseHandler implements ResponseHandler<Result>, ApplicationContextAware {
    private Result result;
    private IBizStatus bizStatus = InternalBizStatus.OK;

    protected ApplicationContext context;

    private List<HttpMessageConverter> converters;
    private ResultWrapper resultWrapper;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
        converters = context.getBeanProvider(HttpMessageConverter.class).orderedStream().collect(Collectors.toList());
        resultWrapper = context.getBean(ResultProperties.class).getWrapper();
    }

    @Override
    public Result getResult() {
        if (result == null) {
            long code = bizStatus.getCode();
            return code == 0 ? Result.success() : Result.failure(bizStatus);
        }
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public IBizStatus getBizStatus() {
        return bizStatus;
    }

    public void setBizStatus(IBizStatus bizStatus) {
        this.bizStatus = bizStatus;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void output(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setStatus(bizStatus.getHttpStatus());
        ServletServerHttpResponse servletServerHttpResponse = new ServletServerHttpResponse(response);
        MediaType mediaType = servletServerHttpResponse.getHeaders().getContentType();
        if (mediaType == null) {
            mediaType = MediaType.APPLICATION_JSON;
        }
        for (HttpMessageConverter converter : converters) {
            boolean canWrite = converter.canWrite(Result.class, mediaType);
            if (canWrite) {
                Object data = resultWrapper.isEnable() ? getResult() : getResult().getData();
                converter.write(data, mediaType, servletServerHttpResponse);
                break;
            }
        }
    }
}
