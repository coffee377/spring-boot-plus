package com.voc.dingtalk.service;

import com.taobao.api.ApiException;
import com.taobao.api.TaobaoRequest;
import com.taobao.api.TaobaoResponse;
import com.voc.dingtalk.UrlConst;
import com.voc.dingtalk.exception.DingTalkApiException;
import com.voc.dingtalk.util.ClientUtil;

import java.util.function.Consumer;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/19 19:34
 */
public interface ApiExecutor {

    /**
     * 钉钉接口请求统一封装
     *
     * @param urlConst 接口地址
     * @param request  请求
     * @param consumer Consumer<T>
     * @param <T>      响应
     * @throws DingTalkApiException API异常
     */
    default <T extends TaobaoResponse> void execute(UrlConst urlConst, TaobaoRequest<T> request, Consumer<T> consumer)
            throws DingTalkApiException {
        execute(urlConst, request, null, consumer);
    }

    /**
     * 钉钉接口请求统一封装
     *
     * @param urlConst 接口地址
     * @param request  请求
     * @param session  session
     * @param consumer Consumer<T>
     * @param <T>      响应
     * @throws DingTalkApiException API异常
     */
    default <T extends TaobaoResponse> void execute(UrlConst urlConst, TaobaoRequest<T> request,
                                                             String session, Consumer<T> consumer)
            throws DingTalkApiException {
        T response;
        try {
            response = ClientUtil.of(urlConst).execute(request, session);
            if (Long.parseLong(response.getErrorCode()) == 0L) {
                /* 正常响应后再消费数据 */
                consumer.accept(response);
            } else {
                throw new DingTalkApiException(Long.parseLong(response.getErrorCode()), response.getMsg());
            }
        } catch (ApiException e) {
            throw new DingTalkApiException(Long.parseLong(e.getErrCode()), e.getErrMsg());
        }
    }


}
