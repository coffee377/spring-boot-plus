package com.voc.restful.core.autoconfigure.json.exception;


import com.voc.common.api.biz.BizException;
import com.voc.common.api.biz.IBizStatus;
import com.voc.common.api.biz.InternalBizStatus;

/**
 * JSON 序列号异常
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/19 14:48
 */
public class JsonSerializeException extends BizException {

    @Deprecated
    public JsonSerializeException(IBizStatus bizStatus) {
        super(bizStatus);
    }

    public JsonSerializeException() {
        super(InternalBizStatus.JSON_SERIALIZE_EXCEPTION);
    }
}
