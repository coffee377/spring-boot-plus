package com.voc.restful.core.autoconfigure.json.exception;


import com.voc.common.api.biz.BizException;
import com.voc.common.api.biz.IBizStatus;
import com.voc.common.api.biz.InternalBizStatus;

/**
 * JSON 反序列号异常
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/19 14:48
 */
public class JsonDeserializeException extends BizException {

    @Deprecated
    public JsonDeserializeException(IBizStatus bizStatus) {
        super(bizStatus);
    }

    public JsonDeserializeException() {
        super(InternalBizStatus.JSON_DESERIALIZE_EXCEPTION);
    }

}
