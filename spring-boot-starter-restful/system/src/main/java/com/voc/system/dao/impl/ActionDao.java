package com.voc.system.dao.impl;

import com.voc.system.dao.IActionDao;
import com.voc.system.entity.Action;
import org.springframework.stereotype.Repository;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/02/05 14:49
 */
@Repository
public class ActionDao extends BaseMongoDao<Action, String> implements IActionDao {
    @Override
    public Action add(String name, int mask) {
        return super.add(new Action(name, mask));
    }
}
