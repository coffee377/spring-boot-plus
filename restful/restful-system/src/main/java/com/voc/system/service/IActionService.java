package com.voc.system.service;

import com.voc.restful.core.response.BizException;
import com.voc.system.entity.Action;

import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/02/05 09:11
 */
public interface IActionService {

    /**
     * 获取所有页面操作信息
     *
     * @return List<Action>
     * @throws BizException 业务异常
     */
    List<Action> findAll() throws BizException;

    /**
     * 添加记录
     *
     * @param action PageAction
     * @return boolean
     * @throws BizException 业务异常
     */
    boolean add(Action action) throws BizException;

}
