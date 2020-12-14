package com.voc.restful.core.autoconfigure.json.exception;

import com.voc.restful.core.autoconfigure.json.JsonType;
import com.voc.restful.core.response.BizException;
import com.voc.restful.core.response.IBizStatus;

/**
 * JSON 反序列号异常
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/19 14:48
 */
public class JsonDeserializeException extends BizException {

    private JsonType jsonType;

    public JsonDeserializeException(IBizStatus bizStatus) {
        super(bizStatus);
    }

    public JsonDeserializeException(IBizStatus bizStatus, JsonType jsonType) {
        super(bizStatus);
        this.jsonType = jsonType;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
