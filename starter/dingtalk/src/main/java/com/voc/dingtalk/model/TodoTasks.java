package com.voc.dingtalk.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/11/15 13:41
 */
@Data
public class TodoTasks {
    /**
     * 业务唯一标识
     */
    String sourceId;
    /**
     * 待办标题
     */
    String subject;
    /**
     * 待办描述
     */
    String description;
    /**
     * 截止时间
     */
    LocalDateTime dueTime;
    /**
     * 执行者的 unionId，最大数量 1000
     */
    List<String> executorIds;
    /**
     * 参与者的 unionId，最大数量 1000
     */
    List<String> participantIds;
    String appDetailUrl;
    String pcDetailUrl;
    /**
     * 生成的待办是否仅展示在执行者的待办列表中
     */
    Boolean isOnlyShowExecutor;
    /**
     * 优先级
     */
    Priority priority;
    /**
     * 创建者的 unionId
     */
    String creatorId;

    /**
     * 当前操作者用户的 unionId
     */
    String operatorId;

    class DetailUrl {
        String appUrl;
        String pcUrl;
    }
}
