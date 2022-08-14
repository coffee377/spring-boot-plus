package com.voc.boot.dict.json.jackson;

import com.voc.boot.dict.persist.DataDictItem;
import com.voc.common.api.dict.IDictItem;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/14 19:55
 */
@Data
@ConfigurationProperties(prefix = "dict.serialize")
public class DictItemSerializeProperties {

    /**
     * 全局默认序列化类型
     */
    SerializeType type = SerializeType.VALUE;

    /**
     * {@link DataDictItem#getId()} 字典标识序列化名称
     */
    String id = "id";

    /**
     * {@link IDictItem#getValue()} 字典项实际值序列化名称
     */
    String value = "value";

    /**
     * {@link IDictItem#getText()} 字典项显示值序列化名称
     */
    String text = "text";

    /**
     * {@link IDictItem#getDescription()}} 字典项描述序列化名称
     */
    String description = "description";

}
