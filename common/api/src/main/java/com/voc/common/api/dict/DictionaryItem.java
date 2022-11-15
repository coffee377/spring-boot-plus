package com.voc.common.api.dict;

import com.voc.common.api.bean.ISort;

/**
 * 数据字典项接口
 *
 * @param <V> 字典值泛型
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/06/27 22:48
 */
public interface DictionaryItem<V> extends ISort {

    /**
     * 字典项实际值
     *
     * @return Value
     */
    V getValue();

    /**
     * 字典项显示值
     *
     * @return String
     */
    String getText();

    /**
     * 字典项描述
     *
     * @return String
     */
    String getDescription();

}
