package com.voc.dingtalk.exception;

import com.voc.restful.core.response.BizException;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/18 21:57
 */
public class DingTalkApiException extends BizException {

    public DingTalkApiException(long code, String message) {
        super(code, message);
    }

}
