package com.voc.boot.result.response;

import com.voc.common.api.biz.IBizStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/25 09:27
 */
public interface ResponseHandler<T> {

    /**
     * 输出数据
     *
     * @return Result
     */
    T getResult();

    /**
     * 业务状态描述
     *
     * @return IBizStatus
     */
    IBizStatus getBizStatus();

    default void output(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");
        response.setStatus(getBizStatus().getHttpStatus());
    }


}
