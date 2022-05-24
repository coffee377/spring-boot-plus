package com.voc.common.bean;

import com.voc.restful.core.autoconfigure.json.exception.JsonSerializeException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/12/14 16:20
 */
@Slf4j
public abstract class JsonBean implements IJsonBean {

    @Override
    public String toString() {
        try {
            return toJson();
        } catch (JsonSerializeException e) {
            log.error(e.getMessage());
        }
        return super.toString();
    }

}
