package com.voc.system.entity;

import com.voc.api.autoconfigure.json.IJson;
import com.voc.api.autoconfigure.json.exception.JsonSerializeException;
import com.voc.api.bean.IBean;
import com.voc.api.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/11/09 15:49
 */
@Slf4j
public class JsonEntity implements IBean {

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
        IJson json = SpringUtil.getBean(IJson.class);
        return json.serializer(this);
    }

}
