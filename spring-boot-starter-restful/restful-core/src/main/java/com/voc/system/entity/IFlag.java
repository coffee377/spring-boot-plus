package com.voc.system.entity;

import com.voc.api.bean.IBean;

/**
 * Created with IntelliJ IDEA.
 *
 * @param <F> 数据状态标识泛型
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/06/27 22:20
 */
public interface IFlag<F> extends IBean {

    /**
     * 获取数据状态标识
     *
     * @return 状态标识
     */
    F getFlag();

    /**
     * 数据状态赋值
     *
     * @param flag F
     */

    void setFlag(F flag);

}
