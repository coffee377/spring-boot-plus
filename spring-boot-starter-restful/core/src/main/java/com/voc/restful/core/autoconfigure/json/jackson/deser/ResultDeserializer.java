package com.voc.restful.core.autoconfigure.json.jackson.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.voc.restful.core.response.Result;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/04/25 13:29
 */
@Component
public class ResultDeserializer extends JsonDeserializer<Result> {
    @Override
    public Result deserialize(JsonParser p, DeserializationContext context) throws IOException, JsonProcessingException {
        return null;
    }
}
