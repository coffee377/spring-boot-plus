package com.voc.system.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.voc.common.api.entity.IEntity;
import com.voc.restful.core.entity.BaseEntity;
import com.voc.system.constant.Table;
import com.voc.system.entity.enums.ComponentFileSuffix;
import com.voc.system.entity.enums.ComponentPosition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 持久化对象 - 前端组件
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/07 13:57
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = Table.COMPONENT)
public class ComponentPO extends BaseEntity<String> implements IEntity<String> {

    /**
     * 菜单 ID
     */
    private String mid;

    /**
     * 组件所在目录
     */
    private ComponentPosition position;

    /**
     * 组件名称
     */
    private String name;

    /**
     * 组件后缀名
     */
    private ComponentFileSuffix suffix;

    /**
     * 重定向地址
     */
    private String redirect;

    /**
     * 路由名称
     */
    private String routeName;

    /**
     * 组件属性配置
     */
    private Map<String, Object> configuration;

}
