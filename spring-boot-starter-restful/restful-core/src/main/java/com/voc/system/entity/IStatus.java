package com.voc.system.entity;

import com.voc.api.bean.IBean;

/**
 * Created with IntelliJ IDEA.
 *
 * @param <S> 数据使用状态泛型
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/06/27 22:21
 */
public interface IStatus<S> extends IBean {

    /**
     * 获取数据使用状态
     *
     * @return 状态标识
     */
    S getStatus();

    /**
     * 数据使用状态赋值
     *
     * @param status UsingStatus
     */
    void setStatus(S status);

}
