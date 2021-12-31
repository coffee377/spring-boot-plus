package com.voc.restful.core.autoconfigure.json.jackson.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.voc.restful.core.autoconfigure.json.ResultFieldName;
import com.voc.restful.core.props.ApiProperties;
import com.voc.restful.core.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/04/25 13:27
 */
@Slf4j
public class ResultSerializer extends JsonSerializer<Result> {

    private final ResultFieldName resultField;

    public ResultSerializer(ApiProperties apiProperties) {
        this.resultField = apiProperties.getJson().getResult();
    }

    @Override
    public void serialize(Result result, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeBooleanField(resultField.getSuccess(), result.isSuccess());
        gen.writeNumberField(resultField.getCode(), result.getCode());
        gen.writeStringField(resultField.getMessage(), result.getMessage());
        if (!ObjectUtils.isEmpty(result.getData())) {
            gen.writeObjectField(resultField.getData(), result.getData());
        }
        gen.writeEndObject();
    }
}
