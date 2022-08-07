package com.voc.boot.dict.converter;

import com.voc.common.api.dict.EnumDictItem;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

import java.util.Set;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/07/02 23:27
 */
public class EnumDictItemConverter implements ConditionalGenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return null;
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        Class<? extends EnumDictItem> target = (Class<? extends EnumDictItem>) targetType.getType();
        return EnumDictItem.find(target, source).orElse(null);
    }

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return EnumDictItem.class.isAssignableFrom(targetType.getType());
    }

}
