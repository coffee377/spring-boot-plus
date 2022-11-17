package com.voc.dingtalk.controller;

import com.aliyun.dingtalktodo_1_0.models.*;
import com.voc.dingtalk.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/11/16 10:44
 */
@Slf4j
@RestController
@RequestMapping("/todo")
public class TodoController {

    @Resource
    private TodoService todoService;

    @PostMapping
    public CreateTodoTaskResponseBody createTodo(@RequestBody CreateTodoTaskRequest request) {
        return todoService.create(request.getCreatorId(), request);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTodoTask(@PathVariable String taskId, @RequestBody DeleteTodoTaskRequest request) {
        todoService.remove(request.getOperatorId(), taskId, request);
    }

    @PutMapping("/{taskId}")
    public void updateTodoTask(@PathVariable String taskId, @RequestBody UpdateTodoTaskRequest request) {
        todoService.update(request.getOperatorId(), taskId, request);
    }

    @PutMapping("/{taskId}/status")
    public void updateTodoTaskExecutorStatus(@PathVariable String taskId, @RequestBody UpdateTodoTaskExecutorStatusRequest request) {
        todoService.updateExecutorStatus(request.getOperatorId(), taskId, request);
    }

    @GetMapping
    public QueryTodoTasksResponseBody query(@RequestBody QueryTodoTasksRequest request, @RequestParam String unionId) {
        return todoService.query(unionId, request);
    }


}
