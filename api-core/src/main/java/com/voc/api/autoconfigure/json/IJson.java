package com.voc.api.autoconfigure.json;

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
     */
    String serializer(Object obj) throws Exception;

    /**
     * 反序列化
     *
     * @param jsonSting  json 字符串
     * @param targetType Class<T>
     * @param <T>        泛型
     * @return T
     */
    <T> T deserializer(String jsonSting, Class<T> targetType) throws Exception;

}
