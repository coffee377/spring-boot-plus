package com.voc.boot.dict.persist;

import com.voc.common.api.bean.Identify;
import com.voc.common.api.dict.DictionaryItem;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/06 22:50
 */
public interface DataDictItem<V> extends DictionaryItem<V>, Identify<String> {
}
