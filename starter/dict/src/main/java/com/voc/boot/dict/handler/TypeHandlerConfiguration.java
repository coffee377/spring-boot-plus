package com.voc.boot.dict.handler;

import com.voc.common.api.dict.FuncEnumDictItem;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @since 0.1.2
 */
@Import({MyBatisTypeHandlerConfiguration.class, MyBatisPlusTypeHandlerConfiguration.class})
public class TypeHandlerConfiguration {

    @Bean
    FuncEnumProviderBuilder funcEnumProviderBuilder(ObjectProvider<FuncEnumProvider> providers) {
        return new FuncEnumProviderBuilder(providers);
    }

    @Bean
    Consumer<TypeHandlerRegistry> typeHandlerRegistryConsumer(FuncEnumProviderBuilder builder) {
        return new Consumer<TypeHandlerRegistry>() {
            @Override
            public void accept(TypeHandlerRegistry registry) {
                /* 枚举字典 TypeHandler */
                registry.register(EnumDictItemTypeHandler.class);
                /* 功能点字典 TypeHandler */
                // TODO: 2022/11/20 0:37 获取自动注册
//                register(registry, builder);
            }

            private void register(TypeHandlerRegistry registry, FuncEnumProviderBuilder builder) {
                //                        registry.getInstance(aClass, FuncEnumDictItemTypeHandler.class))
                Collection<Class<? extends FuncEnumDictItem<?>>> classes = builder.get();
                List<FuncEnumDictItemTypeHandler<? extends FuncEnumDictItem<?>>> typeHandlers = classes.stream().map(new Function<Class<? extends FuncEnumDictItem<?>>, FuncEnumDictItemTypeHandler<? extends FuncEnumDictItem<?>>>() {
                    @Override
                    public FuncEnumDictItemTypeHandler<? extends FuncEnumDictItem<?>> apply(Class<? extends FuncEnumDictItem<?>> aClass) {
//                    return new FuncEnumDictItemTypeHandler<>();
                        return null;
                    }
                }).collect(Collectors.toList());

//            List<FuncEnumDictItemTypeHandler<? extends FuncEnumDictItem>> typeHandlers = builder.get()
//                    .stream().map((Function<Class<? extends FuncEnumDictItem<?>>, FuncEnumDictItemTypeHandler<?
//                            extends FuncEnumDictItem<?>>>) FuncEnumDictItemTypeHandler::new).collect(Collectors.toList());
                typeHandlers.forEach(typeHandler -> {
//                registry.register(typeHandler);
//                Class<List<? extends FuncEnumDictItem<?>>> listClass1 = (Class<List<? extends FuncEnumDictItem<?>>>) typeHandler.getListClass();
//                Class<? extends List<? extends FuncEnumDictItem<?>>> listClass2 = typeHandler.getListClass();
//                Class<? extends List<? extends FuncEnumDictItem>> listClass = typeHandler.getListClass();
//                registry.register(listClass1, null, typeHandler);
                });
//            registry.register(FuncEnumDictItemTypeHandler.class);
            }


        };
    }

}
