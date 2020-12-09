package com.voc.api.autoconfigure.json.exception;

import com.voc.api.response.BizException;
import com.voc.api.response.IBizStatus;

/**
 * JSON 序列号异常
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/19 14:48
 */
public class JsonSerializeException extends BizException {

    public JsonSerializeException(IBizStatus bizStatus) {
        super(bizStatus);
    }

}
