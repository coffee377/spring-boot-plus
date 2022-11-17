package com.voc.dingtalk.service;

import com.aliyun.dingtalktodo_1_0.models.*;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/27 22:13
 * @since 0.1.2
 */
public interface TodoService {

    /**
     * 创建待办任务
     *
     * @param unionId 创建用户的钉钉 unionId
     * @param request 请求数据
     * @return CreateTodoTaskResponseBody
     */
    CreateTodoTaskResponseBody create(String unionId, CreateTodoTaskRequest request);

    /**
     * 删除订单待办任务
     *
     * @param unionId 当前访问资源所归属用户的 unionId，和操作者的 unionId 保持一致
     * @param taskId  钉钉待办 ID
     * @param request 删除待办数据
     */
    void remove(String unionId, String taskId, DeleteTodoTaskRequest request);

    /**
     * 更新钉钉待办任务
     *
     * @param unionId 当前访问资源所归属用户的unionId，和操作者的unionId保持一致
     * @param taskId  钉钉待办 ID
     * @param request 更新待办数据
     */
    void update(String unionId, String taskId, UpdateTodoTaskRequest request);

    /**
     * 更新钉钉待办执行者状态
     *
     * @param unionId 当前访问的资源所归属用户的 unionId
     * @param taskId  钉钉待办 ID
     * @param request 请求数据
     */
    void updateExecutorStatus(String unionId, String taskId, UpdateTodoTaskExecutorStatusRequest request);

    /**
     * 查询企业下用户待办列表
     *
     * @param unionId 用户的unionId
     * @param request 查询请求数据
     * @return QueryTodoTasksResponseBody
     */

    QueryTodoTasksResponseBody query(String unionId, QueryTodoTasksRequest request);
}
