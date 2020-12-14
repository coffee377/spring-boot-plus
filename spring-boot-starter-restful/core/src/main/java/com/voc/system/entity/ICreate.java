package com.voc.system.entity;

import com.voc.restful.core.bean.IBean;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/06/27 22:24
 * @param <UPK> 用户表主键类型
 */
public interface ICreate<UPK> extends IBean {

    /**
     * 获取创建人
     *
     * @return 创建人 ID
     */
    UPK getCreatedBy();

    /**
     * 创建人赋值
     *
     * @param createdBy 创建人
     */
    void setCreatedBy(UPK createdBy);

    /**
     * 获取创建时间
     *
     * @return 创建时间戳
     */
    Date getCreatedTime();

    /**
     * 创建时间赋值
     *
     * @param createdTime 时间戳
     */
    void setCreatedTime(Date createdTime);

}
