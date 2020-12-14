package com.voc.restful.core.autoconfigure.json;

import com.voc.restful.core.autoconfigure.json.exception.JsonDeserializeException;
import com.voc.restful.core.autoconfigure.json.exception.JsonSerializeException;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/27 15:12
 */
public interface IJson {

    /**
     * 序列化
     *
     * @param obj Object
     * @return String
     * @throws JsonSerializeException 序列化异常
     */

    String serializer(Object obj) throws JsonSerializeException;

    /**
     * 反序列化
     *
     * @param jsonSting  json 字符串
     * @param targetType Class<T>
     * @param <T>        泛型
     * @return T
     * @throws JsonDeserializeException 反序列化异常
     */
    <T> T deserializer(String jsonSting, Class<T> targetType) throws JsonDeserializeException;

}
