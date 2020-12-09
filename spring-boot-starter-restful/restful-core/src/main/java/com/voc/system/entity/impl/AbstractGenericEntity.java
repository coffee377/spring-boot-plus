package com.voc.system.entity.impl;

import com.voc.system.entity.IGeneric;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/19 18:20
 */
@Getter
@Setter
public abstract class AbstractGenericEntity<PK, UPK> implements IGeneric<PK, UPK> {

    private PK id;

    private UPK createdBy;

    private Date createdTime;

    private UPK updatedBy;

    private Date updatedTime;

}
