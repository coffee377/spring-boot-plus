package com.voc.dingtalk.service.impl;

import com.aliyun.dingtalktodo_1_0.models.*;
import com.voc.dingtalk.DingTalkRestTemplateCustomizer;
import com.voc.dingtalk.service.AppService;
import com.voc.dingtalk.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/27 22:27
 */
@Slf4j
@Service
public class DefaultTodoService implements TodoService {
    private final RestTemplate restTemplate;
    private final AuditorAware<String> currentUser;

    public DefaultTodoService(RestTemplateBuilder builder, AppService appService,
                              @Autowired(required = false) AuditorAware<String> currentUser) {
        this.currentUser = currentUser;
        this.restTemplate = builder.rootUri("https://api.dingtalk.com")
                .additionalCustomizers(new DingTalkRestTemplateCustomizer(appService))
                .build();
    }

    @Override
    public CreateTodoTaskResponseBody create(String unionId, CreateTodoTaskRequest todoTaskRequest) {
        if (!unionId.equals(todoTaskRequest.getCreatorId())) {
            todoTaskRequest.setCreatorId(unionId);
        }
        CreateTodoTaskResponseBody responseBody = restTemplate.postForObject("/v1.0/todo/users/{unionId}/tasks?operatorId={operatorId}",
                todoTaskRequest, CreateTodoTaskResponseBody.class, unionId, todoTaskRequest.getOperatorId());
        log.warn("{}", responseBody);
        return responseBody;
    }

    @Override
    public void remove(String unionId, String taskId, DeleteTodoTaskRequest request) {
        restTemplate.delete("/v1.0/todo/users/{unionId}/tasks/{taskId}?operatorId={operatorId}", unionId, taskId, request.getOperatorId());
    }

    @Override
    public void update(String unionId, String taskId, UpdateTodoTaskRequest request) {
        restTemplate.put("/v1.0/todo/users/{unionId}/tasks/{taskId}?operatorId={operatorId}", request, unionId, taskId, request.getOperatorId());
    }

    @Override
    public void updateExecutorStatus(String unionId, String taskId, UpdateTodoTaskExecutorStatusRequest request) {
        restTemplate.put("/v1.0/todo/users/{unionId}/tasks/{taskId}/executorStatus?operatorId={operatorId}", request, unionId, taskId, request.getOperatorId());
    }

    @Override
    public QueryTodoTasksResponseBody query(String unionId, QueryTodoTasksRequest request) {
        return restTemplate.postForObject("/v1.0/todo/users/{unionId}/org/tasks/query", request, QueryTodoTasksResponseBody.class, unionId);
    }

}
