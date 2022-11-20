package com.voc.boot.dict.handler;

import com.voc.common.api.dict.FuncEnumDictItem;
import org.springframework.beans.factory.ObjectProvider;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/11/19 18:14
 */
public class FuncEnumProviderBuilder {
    private final Set<Class<? extends FuncEnumDictItem<?>>> classes;

    public FuncEnumProviderBuilder(ObjectProvider<FuncEnumProvider> providers) {
        classes = providers.orderedStream().reduce(new HashSet<>(), (classes, funcEnumProvider) -> {
            classes.addAll(funcEnumProvider.get());
            return classes;
        }, (classes, classes2) -> classes);
    }

    public Collection<Class<? extends FuncEnumDictItem<?>>> get() {
        return classes;
    }


}
