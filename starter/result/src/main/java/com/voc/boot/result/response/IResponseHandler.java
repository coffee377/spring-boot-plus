package com.voc.boot.result.response;

import com.voc.boot.result.Result;
import com.voc.common.api.biz.IBizStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/25 09:27
 */
public interface IResponseHandler {

    /**
     * 输出数据
     *
     * @return Result
     */
    Result getResult();

    /**
     * 业务状态描述
     *
     * @return IBizStatus
     */
    IBizStatus getBizStatus();

    /**
     * 输出前置处理
     *
     * @param response HttpServletResponse
     * @throws IOException IO 异常
     */
    default void beforeWrite(HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");
        response.setStatus(getBizStatus().getHttpStatus());
    }

    /**
     * 输出响应数据
     *
     * @param response HttpServletResponse
     * @throws IOException IO 异常
     */
    default void write(HttpServletResponse response) throws IOException {
        this.beforeWrite(response);
//        for (HttpMessageConverter<Result> messageConverter : httpMessageConverter) {
//            boolean canWrite = messageConverter.canWrite(Result.class, MediaType.APPLICATION_JSON);
//            if (canWrite) {
//                ServletServerHttpResponse servletServerHttpResponse = new ServletServerHttpResponse(response);
//                messageConverter.write(getResult(), null, servletServerHttpResponse);
//                break;
//            }
//        }
    }


}
