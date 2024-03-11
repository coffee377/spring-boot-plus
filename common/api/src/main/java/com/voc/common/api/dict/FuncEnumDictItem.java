package com.voc.common.api.dict;

import com.voc.common.api.func.FunctionPoint;
import com.voc.common.api.func.Functions;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * 功能点枚举
 *
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @since 0.1.2
 */
public interface FuncEnumDictItem<V> extends EnumDictItem<V>, FunctionPoint, Function<BigInteger, V> {

    @Override
    default V getValue() {
        return apply(get());
    }

    @Override
    default String getName() {
        return getDescription();
    }

    @Override
    default int getPosition() {
        return ordinal() + 1;
    }

    @Override
    default String getDescription() {
        return EnumDictItem.super.getDescription();
    }

    /**
     * 获取功能点列表
     *
     * @param type      Class<T>
     * @param functions 功能点描述
     * @param <T>       枚举类型
     * @return List<T>
     */
    static <T extends FuncEnumDictItem> List<T> getFunctions(Class<T> type, Functions functions) {
        BigInteger func = functions.get();
        if (func != null) {
            return EnumDictItem.findByCondition(type, item -> item.get().or(func).equals(func));
        }
        return Collections.emptyList();
    }
}
