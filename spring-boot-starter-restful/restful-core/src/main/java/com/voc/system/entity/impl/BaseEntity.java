package com.voc.system.entity.impl;

import com.voc.api.enums.DataFlag;
import com.voc.api.enums.UsingStatus;
import com.voc.system.entity.IBase;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/19 18:34
 */
@Getter
@Setter
public abstract class BaseEntity<PK, UPK> extends AbstractGenericEntity<PK, UPK> implements IBase<PK, UPK, DataFlag, UsingStatus> {

    private DataFlag flag;

    private UsingStatus status;

}
