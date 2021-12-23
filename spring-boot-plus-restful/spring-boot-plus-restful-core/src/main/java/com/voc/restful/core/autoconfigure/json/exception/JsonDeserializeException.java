package com.voc.restful.core.autoconfigure.json.exception;

import com.voc.restful.core.response.BizException;
import com.voc.restful.core.response.IBizStatus;
import com.voc.restful.core.response.impl.BaseBizStatus;

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
        super(BaseBizStatus.JSON_DESERIALIZE_EXCEPTION);
    }

}
