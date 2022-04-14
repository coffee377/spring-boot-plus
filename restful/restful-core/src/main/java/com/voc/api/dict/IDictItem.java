package com.voc.api.dict;

/**
 * 数据字典项接口
 *
 * @param <Value> 字典值泛型
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/06/27 22:48
 */
public interface IDictItem<Value> {
    /**
     * 索引
     *
     * @return 序号
     */
    Integer getIndex();

    /**
     * 字典实际值
     *
     * @return Value
     */
    Value getValue();

    /**
     * 字典显示值
     *
     * @return String
     */
    String getText();

    /**
     * 字典项描述
     *
     * @return String
     */
    String getComments();
    
}
