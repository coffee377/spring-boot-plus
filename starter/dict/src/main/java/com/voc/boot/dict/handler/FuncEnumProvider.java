package com.voc.boot.dict.handler;

import com.voc.common.api.dict.FuncEnumDictItem;

import java.util.Collection;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/11/19 17:53
 */
@FunctionalInterface
public interface FuncEnumProvider {

    Collection<Class<? extends FuncEnumDictItem<?>>> get();

}
