package com.voc.system.vo.menu;

import com.voc.system.vo.menu.impl.AutoField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/14 23:03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuVO extends AutoField {
    private String id;
    private String pid;
    private String name;
    private String path;
    private String icon;
    private Integer sort;
    private Object hidden;
    private Boolean disable;
    private Object divider;
    private String target;
    private Integer todo;

//    authority?: Authority;
    List<MenuVO> children;
}
