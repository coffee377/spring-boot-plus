package com.voc.system.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.voc.common.authority.IAuthorityDescriptor;
import com.voc.restful.core.entity.BaseEntity;
import com.voc.persist.entity.IEntity;
import com.voc.system.constant.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 持久化对象 - 接口
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/16 22:20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = Table.API)
public class ApiPO extends BaseEntity<String> implements IEntity<String>, IAuthorityDescriptor {

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 权限掩码
     */
    private Integer mask;

    /**
     * 分类
     */
    private String classify;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 接口匹配地址
     */
    private String urlMapping;

}
