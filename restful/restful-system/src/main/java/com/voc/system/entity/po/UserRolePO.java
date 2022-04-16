package com.voc.system.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.voc.restful.core.entity.BaseEntity;
import com.voc.restful.core.entity.IEntity;
import com.voc.restful.core.persist.PO;
import com.voc.system.constant.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 持久化对象 - 用户角色
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/16 23:42
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = Table.USE_ROLE_RELATION)
@TableName(Table.USER)
public class UserRolePO extends BaseEntity<String> implements IEntity<String>, PO {

    /**
     * 用户 ID
     */
    private String userId;

    /**
     * 角色 ID
     */
    private String roleId;
}
