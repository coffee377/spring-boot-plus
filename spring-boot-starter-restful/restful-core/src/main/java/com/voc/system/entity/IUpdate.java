package com.voc.system.entity;

import com.voc.api.bean.IBean;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @param <UPK> 用户表主键类型
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/06/27 22:25
 */
public interface IUpdate<UPK> extends IBean {

    /**
     * 获取修改人
     *
     * @return 修改人
     */
    UPK getUpdatedBy();

    /**
     * 修改人赋值
     *
     * @param updatedBy 修改人 ID
     */
    void setUpdatedBy(UPK updatedBy);

    /**
     * 获取修改时间
     *
     * @return 时间戳
     */
    Date getUpdatedTime();

    /**
     * 创建时间赋值
     *
     * @param updatedTime 时间戳
     */
    void setUpdatedTime(Date updatedTime);

}
