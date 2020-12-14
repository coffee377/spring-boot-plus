package com.voc.restful.core.response;

import com.voc.restful.core.autoconfigure.json.exception.JsonSerializeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;

/**
 * 接口响应数据统一包装为 Result {@code com.voc.api.response.Result}
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/18 18:53
 * @see Result
 */
@Slf4j
@RestControllerAdvice
@ConditionalOnProperty(prefix = "api.json", name = "automatic-wrapped", havingValue = "true", matchIfMissing = true)
public class ResultAdvice implements ResponseBodyAdvice<Object> {

    private final Class[] annotations = new Class[]{
            RequestMapping.class,
            GetMapping.class,
            PostMapping.class,
            DeleteMapping.class,
            PutMapping.class,
            PatchMapping.class
    };

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
        return this.validateMethod(methodParameter);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Object out;
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        /* 响应结果为 Result 直接返回 */
        if (body instanceof Result) {
            out = body;
        }
        /* 响应结果为字符 */
        else if (body instanceof String) {
            Result<Object> result = Result.builder().success(body).build();
            try {
                out = result.toJson();
            } catch (JsonSerializeException e) {
                log.error(e.getMessage(), e);
                out = Result.failure(BaseBizStatus.JSON_SERIALIZE_EXCEPTION).toJson();
            }
        } else {
            out = Result.builder().success(body).build();
        }
        return out;
    }

    private boolean validateMethod(MethodParameter methodParameter) {
        AnnotatedElement element = methodParameter.getAnnotatedElement();
        return Arrays.stream(annotations).filter(Class::isAnnotation).anyMatch(element::isAnnotationPresent);
    }
}
