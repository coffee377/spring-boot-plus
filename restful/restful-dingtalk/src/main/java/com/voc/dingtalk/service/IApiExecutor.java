package com.voc.dingtalk.service;

import com.taobao.api.ApiException;
import com.taobao.api.TaobaoRequest;
import com.taobao.api.TaobaoResponse;
import com.voc.dingtalk.exception.DingTalkApiException;
import com.voc.dingtalk.url.UrlPath;
import com.voc.dingtalk.util.ClientUtils;

import java.util.function.Consumer;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/19 19:34
 */
public interface IApiExecutor {

    /**
     * 钉钉接口请求统一封装
     *
     * @param path         接口路径
     * @param request      请求
     * @param accessKey    应用ID
     * @param accessSecret 应用密钥
     * @param suiteTicket  suiteTicket
     * @param corpId       企业ID
     * @param consumer     Consumer<T>
     * @param <T>          响应
     * @throws DingTalkApiException API异常
     */
    default <T extends TaobaoResponse> void execute(UrlPath path, TaobaoRequest<T> request, String accessKey,
                                                    String accessSecret, String suiteTicket, String corpId,
                                                    Consumer<T> consumer)
            throws DingTalkApiException {
        T response;
        try {
            response = ClientUtils.of(path).execute(request, accessKey, accessSecret, suiteTicket, corpId);
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

    /**
     * 钉钉接口请求统一封装
     *
     * @param path         接口路径
     * @param request      请求
     * @param accessKey    应用ID
     * @param accessSecret 应用密钥
     * @param suiteTicket  suiteTicket
     * @param consumer     Consumer<T>
     * @param <T>          响应
     * @throws DingTalkApiException API异常
     */
    default <T extends TaobaoResponse> void execute(UrlPath path, TaobaoRequest<T> request, String accessKey,
                                                    String accessSecret, String suiteTicket, Consumer<T> consumer) throws DingTalkApiException {
        this.execute(path, request, accessKey, accessSecret, suiteTicket, null, consumer);
    }

    /**
     * 钉钉接口请求统一封装
     *
     * @param path         接口路径
     * @param request      请求
     * @param accessKey    应用ID
     * @param accessSecret 应用密钥
     * @param consumer     Consumer<T>
     * @param <T>          响应
     * @throws DingTalkApiException API异常
     */
    default <T extends TaobaoResponse> void execute(UrlPath path, TaobaoRequest<T> request, String accessKey,
                                                    String accessSecret, Consumer<T> consumer) throws DingTalkApiException {
        this.execute(path, request, accessKey, accessSecret, null, null, consumer);
    }

    /**
     * 钉钉接口请求统一封装
     *
     * @param path     接口路径
     * @param request  请求
     * @param consumer Consumer<T>
     * @param <T>      响应
     * @throws DingTalkApiException API异常
     */
    default <T extends TaobaoResponse> void execute(UrlPath path, TaobaoRequest<T> request, Consumer<T> consumer)
            throws DingTalkApiException {
        execute(path, request, null, consumer);
    }

    /**
     * 钉钉接口请求统一封装
     *
     * @param path     接口路径
     * @param request  请求
     * @param session  session
     * @param consumer Consumer<T>
     * @param <T>      响应
     * @throws DingTalkApiException API异常
     */
    default <T extends TaobaoResponse> void execute(UrlPath path, TaobaoRequest<T> request, String session,
                                                    Consumer<T> consumer)
            throws DingTalkApiException {
        T response;
        try {
            response = ClientUtils.of(path).execute(request, session);
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
