package com.voc.boot.dict.json.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.voc.common.api.dict.EnumDictItem;
import com.voc.common.api.dict.IDictItem;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/09 14:05
 */
public class DictModule extends SimpleModule {

    public DictModule() {
        super("DictModule");
        this.addSerializer(new DictItemSerializer());
    }

    @Override
    public void setupModule(SetupContext context) {
        super.setupModule(context);
//        SimpleSerializers simpleSerializers = new SimpleSerializers();
//        simpleSerializers.addSerializer(resultSerializer);
//        context.addSerializers(simpleSerializers);
    }
}
