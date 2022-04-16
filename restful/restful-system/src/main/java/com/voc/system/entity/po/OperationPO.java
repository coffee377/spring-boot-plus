package com.voc.system.entity.po;

import com.voc.api.authority.IAuthorityDescriptor;
import com.voc.restful.core.entity.BaseEntity;
import com.voc.restful.core.entity.IEntity;
import com.voc.system.constant.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 持久化对象 - 操作
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/02/05 09:12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = Table.OPERATION)
public class OperationPO extends BaseEntity<String> implements IEntity<String>, IAuthorityDescriptor {

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
    private int mask;

}
