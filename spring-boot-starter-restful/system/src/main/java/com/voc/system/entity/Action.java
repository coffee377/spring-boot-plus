package com.voc.system.entity;

import com.voc.restful.core.entity.BaseEntity;
import com.voc.system.constant.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/02/05 09:12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = TableName.ACTION)
public class Action extends BaseEntity<String> implements IAction<String> {

    @Field(name = "name", order = 1)
    private String name;

    @Field(name = "mask", order = 2)
    private int mask;

    @Version
    private Long version;

    public Action() {
    }

    public Action(String id, String name, int mask) {
        this.id = id;
        this.name = name;
        this.mask = mask;
    }

    public Action(String name, int mask) {
        this(null, name, mask);
    }

}
