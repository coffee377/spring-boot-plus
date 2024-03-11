package com.voc.boot.dict.json.jackson;

import com.voc.boot.dict.json.annotation.DictSerialize;
import com.voc.boot.dict.persist.DataDictItem;
import com.voc.common.api.dict.DictionaryItem;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/14 19:55
 */
@Data
@ConfigurationProperties(prefix = "dict.serialize")
public class DictSerializeProperties {

    /**
     * 全局默认序列化类型
     */
    List<DictSerialize.Scope> scopes = Collections.singletonList(DictSerialize.Scope.VALUE);

    /**
     * {@link DataDictItem#getId()} 字典标识序列化名称
     */
    String id = "id";

    /**
     * {@link DictionaryItem#getValue()} 字典项实际值序列化名称
     */
    String value = "value";

    /**
     * {@link DictionaryItem#getLabel()} 字典项显示值序列化名称
     */
    String label = "label";

    /**
     * {@link DictionaryItem#getDescription()}} 字典项描述序列化名称
     */
    String description = "description";

}
