package com.voc.system.dao;

import com.voc.system.entity.Action;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/02/05 15:12
 */
public interface IActionDao extends IMongoDao<Action, String> {

//    /**
//     * 获取最大的掩码值
//     *
//     * @return long
//     */
//    int getLatestMask();

    /**
     * 添加 action
     *
     * @param name 名称
     * @param mask 掩码
     * @return Action
     */
    Action add(String name, int mask);
//
//    Action update(Action action);
//
//    /**
//     * 删除指定掩码的 action
//     *
//     * @param mask 掩码
//     */
//    void deleteByMasks(int... mask);

}
