package com.voc.system.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.voc.restful.core.entity.BaseEntity;
import com.voc.restful.core.entity.ITreeEntity;
import com.voc.system.constant.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/19 18:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = TableName.ROLE)
public class Role extends BaseEntity<String> implements ITreeEntity<String> {

    /**
     * 父级角色ID
     */
    @JsonAlias({"parentId", "pid"})
    @JsonProperty("pid")
    private String parentId;

    /**
     * 角色编码
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

}
