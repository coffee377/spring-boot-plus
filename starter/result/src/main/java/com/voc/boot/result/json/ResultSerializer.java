package com.voc.boot.result.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.voc.boot.result.IPageResult;
import com.voc.boot.result.IResult;
import com.voc.boot.result.properties.JsonField;
import com.voc.boot.result.properties.ResultProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/04/25 13:27
 */
@Slf4j
public class ResultSerializer extends JsonSerializer<IResult> {

    private final ResultProperties resultProperties;
    private final JsonField property;

    public ResultSerializer(ResultProperties resultProperties) {
        this.resultProperties = resultProperties;
        this.property = resultProperties.getJson();
    }

    @Override
    public void serialize(IResult result, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeBooleanField(property.getSuccess(), result.isSuccess());
        if (!ObjectUtils.isEmpty(result.getCode())) {
            if (resultProperties.isCodeAsNumber()) {
                gen.writeNumberField(property.getCode(), Long.parseLong(result.getCode()));
            } else {
                gen.writeStringField(property.getCode(), result.getCode());
            }
        }
        if (!ObjectUtils.isEmpty(result.getMessage())) {
            gen.writeStringField(property.getMessage(), result.getMessage());
        }
        if (!ObjectUtils.isEmpty(result.getData())) {
            gen.writeObjectField(property.getData(), result.getData());
        }
        if (result instanceof IPageResult) {
            Integer total = ((IPageResult<?>) result).getTotal();
            if (!ObjectUtils.isEmpty(total)) {
                gen.writeNumberField(property.getTotal(), total);
            }
        }
        gen.writeEndObject();
    }

    @Override
    public Class<IResult> handledType() {
        return IResult.class;
    }
}
