package com.voc.api.config.json.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.voc.api.config.json.JsonProperties;

import java.io.IOException;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/27 23:12
 */
public class DateJsonDeserializer extends JsonDeserializer<Object> {
    private JsonProperties jsonProperties;

    public DateJsonDeserializer(JsonProperties jsonProperties) {
        this.jsonProperties = jsonProperties;
    }

    @Override
    public Object deserialize(JsonParser p, DeserializationContext context) throws IOException, JsonProcessingException {
        TreeNode treeNode = p.getCodec().readTree(p);
//        treeNode.
        return p.getValueAsString();
    }
}
