package com.voc.system.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.voc.restful.core.entity.BaseEntity;
import com.voc.restful.core.entity.IEntity;
import com.voc.restful.core.persist.entity.PersistEntity;
import com.voc.system.constant.Table;
import com.voc.system.entity.enums.AuthorityType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 持久化对象 - 权限
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/14 15:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = Table.AUTHORITY)
public class AuthorityPO extends BaseEntity<String> implements IEntity<String>, PersistEntity {

    /**
     * 权限类型
     */
    AuthorityType type;

    /**
     * 相关资源 id
     */
    private String resourceId;

    /**
     * 相关父级资源 id
     */
    private String parentResourceId;
}
