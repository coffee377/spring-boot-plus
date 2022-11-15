package com.voc.boot.dict.json.jackson;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.voc.common.api.dict.DictionaryItem;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/15 09:59
 */
public class DictItemConverter extends StdConverter<DictionaryItem, Object> {
    @Override
    public Object convert(DictionaryItem dictItem) {
        return "dictItem.getText()";
    }
}
