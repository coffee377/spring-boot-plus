package com.voc.system.entity;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/11 15:42
 */
public interface IRole<PK, UPK> extends IGeneric<PK, UPK> {

    /**
     * 角色编码
     *
     * @return String
     */
    String getCode();

    /**
     * 角色名称
     *
     * @return String
     */
    String getName();

}
