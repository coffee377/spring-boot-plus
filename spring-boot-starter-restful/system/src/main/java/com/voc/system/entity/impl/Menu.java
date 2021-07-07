package com.voc.system.entity.impl;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.voc.restful.core.entity.BaseEntity;
import com.voc.restful.core.entity.ITreeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/04/26 15:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "sys_menu")
public class Menu extends BaseEntity<String> implements ITreeEntity<String> {

    /**
     * 父菜单ID
     */
    @JsonAlias({"parentId", "pid"})
    @JsonProperty("pid")
    private String parentId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单排序
     */
    private Integer sort;

    /**
     * 当前页面路径名
     */
    private String path;

    /**
     * 菜单隐藏类型
     * all 在菜单中隐藏自己和子节点
     * self 隐藏自己，并将子节点提升到与自己平级
     * children 在菜单中隐藏子节点
     */
    private String hidden;

    /**
     * 禁用菜单
     */
    private Boolean disable;

    /**
     * 在其前后是否插入分隔符的位置
     * 'before' | 'after' | 'all'
     */
    private String divider;

    /**
     * 组件目录类型 layout | page | path
     */
    private String type;

    /**
     * 组件名称
     */
    private String component;

    /**
     * 组件属性配置
     */
    private Object props;

}
