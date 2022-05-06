package com.voc.restful.core.response;

import com.voc.restful.core.autoconfigure.json.JsonWrapper;
import com.voc.restful.core.autoconfigure.json.exception.JsonSerializeException;
import com.voc.restful.core.response.impl.BaseBizStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 接口响应数据统一包装为 Result {@code com.voc.restful.core.response.Result}
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/18 18:53
 * @see Result
 */
@Slf4j
@RestControllerAdvice
@ConditionalOnProperty(prefix = "api.json.wrapper", name = "enable", havingValue = "true", matchIfMissing = true)
public class ResultAdvice implements ResponseBodyAdvice<Object>, ApplicationContextAware {

    private final Set<String> ignoredClassName = new HashSet<>();

    private final Class[] annotations = new Class[]{
            RequestMapping.class,
            GetMapping.class,
            PostMapping.class,
            DeleteMapping.class,
            PutMapping.class,
            PatchMapping.class
    };

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        JsonWrapper jsonWrapper = applicationContext.getBean(JsonWrapper.class);
        List<String> ignoredClass = jsonWrapper.getIgnoredClass();
        if (ignoredClass != null) {
            ignoredClassName.addAll(ignoredClass);
        }
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
        return !this.isOpenApi(methodParameter) && this.supports(methodParameter);
    }

    private boolean isOpenApi(MethodParameter methodParameter){
        String canonicalName = methodParameter.getDeclaringClass().getCanonicalName();
        return ignoredClassName.stream().anyMatch(clazz -> clazz.equals(canonicalName));
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

    private boolean supports(MethodParameter methodParameter) {
        Method method = methodParameter.getMethod();
        assert method != null;
        AnnotatedElement element = methodParameter.getAnnotatedElement();
        /* 前置条件，必须是 annotations 中指定注解的方法 */
        boolean preCondition = Arrays.stream(annotations).filter(Class::isAnnotation).anyMatch(element::isAnnotationPresent);
        return preCondition && this.methodWrapper(method);
    }

    /**
     * 类上指定方法是否包装响应结果
     *
     * @return boolean
     */
    private boolean methodWrapper(Method method) {
        /* 1. 方法上的注解优先 */
        ResponseResult methodAnnotation = AnnotationUtils.getAnnotation(method, ResponseResult.class);
        if (methodAnnotation != null) {
            log.debug("方法上注解 value：{} wrapped：{}", methodAnnotation.value(), methodAnnotation.wrapped());
            return methodAnnotation.value();
        }

        /* 2. 方法上不存在注解时使用类上注解 */
        Class<?> clazz = method.getDeclaringClass();
        ResponseResult classAnnotation = AnnotationUtils.getAnnotation(clazz, ResponseResult.class);
        if (classAnnotation != null) {
            log.debug("类上注解 value：{} wrapped：{}", classAnnotation.value(), classAnnotation.wrapped());
            return classAnnotation.value();
        }
        /* 3. 都不存在时返回 true */
        return true;
    }
}
