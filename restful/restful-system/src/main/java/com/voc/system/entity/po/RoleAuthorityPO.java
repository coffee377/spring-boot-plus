package com.voc.system.entity.po;

import com.voc.restful.core.entity.BaseEntity;
import com.voc.restful.core.entity.IEntity;
import com.voc.system.constant.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 持久化对象 - 角色权限
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/16 21:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = Table.ROLE_AUTHORITY_RELATION)
public class RoleAuthorityPO extends BaseEntity<String> implements IEntity<String> {

    /**
     * 角色 ID
     */
    private String roleId;

    /**
     * 权限 ID
     */
    private String authorityId;
}
