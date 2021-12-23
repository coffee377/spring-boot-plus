package com.voc.restful.core.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/12 16:47
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseTreeEntity<ID extends Serializable> extends BaseEntity<ID> implements ITreeEntity<ID> {
    /**
     * 父 ID
     */
    private ID parentId;

}
