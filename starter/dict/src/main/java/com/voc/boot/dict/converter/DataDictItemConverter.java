package com.voc.boot.dict.converter;

import com.voc.boot.dict.DataDictItem;
import com.voc.common.api.dict.EnumDictItem;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

import java.util.Set;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/06 23:25
 */
public class DataDictItemConverter implements ConditionalGenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return null;
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        Class<? extends DataDictItem> target = (Class<? extends DataDictItem>) targetType.getType();

        assert source != null;
        String[] split = source.toString().split(":");
        // TODO: 2022/8/6 23:40 持久化数据字典转换
        String dictCode = split[0];
        String value = split[1];
        return null;
    }

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return DataDictItem.class.isAssignableFrom(targetType.getType());
    }

}
