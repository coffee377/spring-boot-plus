package com.voc.system.entity;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/19 18:30
 */
public interface IBase<PK, UPK, F, S> extends IGeneric<PK, UPK>, IFlag<F>, IStatus<S> {
}
