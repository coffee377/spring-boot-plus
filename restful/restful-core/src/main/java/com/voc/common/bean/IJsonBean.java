package com.voc.common.bean;

import com.voc.restful.core.autoconfigure.json.IJson;
import com.voc.restful.core.autoconfigure.json.exception.JsonSerializeException;
import com.voc.restful.core.util.SpringUtils;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/07 14:36
 */
public interface IJsonBean extends IBean {

    /**
     * 序列化对象
     *
     * @return String
     * @throws JsonSerializeException 序列化异常
     */
    default String toJson() throws JsonSerializeException {
        IJson json = SpringUtils.getBean(IJson.class);
        return json.serializer(this);
    }

}
