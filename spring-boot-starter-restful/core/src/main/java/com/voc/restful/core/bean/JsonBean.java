package com.voc.restful.core.bean;

import com.voc.restful.core.autoconfigure.json.IJson;
import com.voc.restful.core.autoconfigure.json.exception.JsonSerializeException;
import com.voc.restful.core.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/12/14 16:20
 */
@Slf4j
public abstract class JsonBean implements IBean {

    @Override
    public String toString() {
        try {
            return toJson();
        } catch (JsonSerializeException e) {
            log.error(e.getMessage());
        }
        return super.toString();
    }

    public String toJson() throws JsonSerializeException {
        IJson json = SpringUtils.getBean(IJson.class);
        return json.serializer(this);
    }

}
