package com.voc.system.entity.po;

import com.voc.restful.core.entity.BaseTreeEntity;
import com.voc.restful.core.entity.ITreeEntity;
import com.voc.system.constant.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 持久化对象 - 角色
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/19 18:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = Table.ROLE)
public class RolePO extends BaseTreeEntity<String> implements ITreeEntity<String> {

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

}
