package com.voc.system.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.voc.api.authority.IAuthorityDescriptor;
import com.voc.restful.core.entity.BaseTreeEntity;
import com.voc.restful.core.entity.ITreeEntity;
import com.voc.system.constant.Table;
import com.voc.system.entity.enums.MenuDividerType;
import com.voc.system.entity.enums.MenuHiddenType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 持久化对象 - 菜单
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/04/26 15:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = Table.MENU)
public class MenuPO extends BaseTreeEntity<String> implements ITreeEntity<String>, IAuthorityDescriptor {

    /**
     * 名称
     */
    private String name;

    /**
     * 当前页面路径名
     */
    private String path;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 菜单隐藏类型
     */
    private MenuHiddenType hidden;

    /**
     * 是否禁用
     */
    private Boolean disable;

    /**
     * 分隔符类型
     */
    private MenuDividerType divider;

    /**
     * 外链打开链接的位置
     */
    private String target;

    /**
     * 权限掩码
     */
    private Integer mask;

}
