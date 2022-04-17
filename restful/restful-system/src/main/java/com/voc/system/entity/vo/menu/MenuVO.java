package com.voc.system.entity.vo.menu;

import com.voc.system.entity.enums.MenuDividerType;
import com.voc.system.entity.enums.MenuHiddenType;
import com.voc.system.entity.vo.menu.impl.AutoField;
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
    /**
     *
     */
    private String id;
    private String pid;
    private String name;
    private String path;
    private String icon;
    private Integer sort;
    private MenuHiddenType hidden;
    private Boolean disable;
    private MenuDividerType divider;
    private String target;
    private Integer todo;

//    authority?: Authority;
    List<MenuVO> children;
}
