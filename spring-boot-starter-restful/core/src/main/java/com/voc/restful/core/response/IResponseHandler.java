package com.voc.restful.core.response;

import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        response.getWriter().write(getResult().toString());
    }

}
