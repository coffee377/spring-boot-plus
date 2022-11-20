package com.voc.boot.dict.handler;

import com.voc.common.api.dict.FuncEnumDictItem;
import com.voc.common.api.func.Functions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.ResolvableType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @since 0.1.2
 */
@Slf4j
public abstract class FuncEnumDictItemTypeHandler<F extends FuncEnumDictItem> extends ListTypeHandler<F> {
    private final Class<F> funcClazz;
//    private final Class<?> value;
//    private Class<T> clazz = null;

//    private JavaType javaType;
//    public FuncEnumDictItemTypeHandler(Class<F> funcClazz) {
////        super(clazz);
////        f = (Class<F>) ResolvableType.forClass(clazz).resolveGeneric(0);
////        this
//    }

    @SuppressWarnings("unchecked")
    public FuncEnumDictItemTypeHandler() {
        funcClazz = (Class<F>) GenericTypeResolver.resolveTypeArgument(getClass(), FuncEnumDictItemTypeHandler.class);
    }


//    public FuncEnumDictItemTypeHandler(Class<F> fClass) {
//        this.f = fClass;
//    }

    public Class<?> getListClass() {
        Class<?> resolve = ResolvableType.forType(getRawType()).resolve();
        return resolve;
    }

    @Override
    protected Object fromList(Set<F> parameter) {
        Functions functions = Functions.builder().functions(parameter).build();
        String result = functions.getFunctions();
        log.debug("{}", result);
        return result;
    }

    @Override
    protected Set<F> toList(Object value) {
        Functions functions = Functions.builder().of(String.valueOf(value)).build();
        List<F> result = FuncEnumDictItem.getFunctions(funcClazz, functions);
        log.debug("{}", result);
        return new HashSet<>(result);
    }

}
