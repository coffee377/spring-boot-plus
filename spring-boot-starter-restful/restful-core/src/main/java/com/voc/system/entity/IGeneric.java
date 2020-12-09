package com.voc.system.entity;

/**
 * Created with IntelliJ IDEA.
 *
 * @param <PK> 主键类型
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/06/27 21:38
 */
public interface IGeneric<PK, UPK> extends ICreate<UPK>, IUpdate<UPK> {

    String ID = "id";

    /**
     * 实体主键
     *
     * @return PK
     */
    PK getId();


    /**
     * 设置主键
     *
     * @param id PK
     */
    void setId(PK id);

}
