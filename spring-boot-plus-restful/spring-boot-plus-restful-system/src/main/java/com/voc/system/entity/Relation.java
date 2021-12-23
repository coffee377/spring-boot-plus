package com.voc.system.entity;

import com.voc.restful.core.entity.BaseEntity;
import com.voc.system.constant.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 关系映射(维护多个实体之间的关系),
 * type 及 相关的两字段不为空，其他字段均为空（除 id 外所有字段构成唯一约束）
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/12 17:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = TableName.RELATION)
public class Relation extends BaseEntity<String> {

    /**
     * 关系映射类型
     */
    @Field(order = 1)
    private Type type;

    /**
     * 用户ID
     */
    @Field(order = 2)
    private String userId;

    /**
     * 角色ID
     */
    @Field(order = 3)
    private String roleId;

    /**
     * 菜单ID
     */
    @Field(order = 4)
    private String menuId;

    /**
     * 接口权限ID
     */
    @Field(order = 6)
    private String authorityId;

    @Getter
    public enum Type {
        /* 用户通过角色获取的权限 */
        USER_ROLE("userId", "roleId"),
        ROLE_MENU("roleId", "menuId"),
        ROLE_AUTHORITY("roleId", "authorityId"),

        /* 用户直接权限 */
        USER_MENU("userId", "menuId"),
        USER_AUTHORITY("userId", "authorityId");

        Type(String id1Name, String id2Name) {
            this.id1Name = id1Name;
            this.id2Name = id2Name;
        }

        private final String id1Name;
        private final String id2Name;
        private final String typeName = "type";
    }
}
