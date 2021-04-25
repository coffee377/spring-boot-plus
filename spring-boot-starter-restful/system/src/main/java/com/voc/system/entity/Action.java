package com.voc.system.entity;

import com.voc.restful.core.entity.JsonEntity;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/02/05 09:12
 */
@Document(collection = "sys_action")
public class Action extends JsonEntity implements IAction<String> {

//    @MongoId(FieldType.STRING)
    private String id;

    @Field(name = "name", order = 1)
    private String name;

    @Field(name = "mask", order = 2)
    private int mask;

    @Version
    private Long version;

    public Action() {
    }

    public Action(String id,String name, int mask) {
        this.id = id;
        this.name = name;
        this.mask = mask;
    }

    public Action(String name, int mask) {
        this(null, name, mask);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getMask() {
        return mask;
    }

    @Override
    public void setMask(int mask) {
        this.mask = mask;
    }

    @Override
    public int hashCode() {
        return this.mask;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Action && ((Action) obj).mask == this.mask;
    }

}
