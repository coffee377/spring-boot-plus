package com.voc.api.autoconfigure.json.exception;

import com.voc.api.autoconfigure.json.JsonType;
import com.voc.api.response.BizException;
import com.voc.api.response.IBizStatus;

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
