package com.voc.system.service.impl;

import com.voc.restful.core.response.BizException;
import com.voc.system.dao.IActionDao;
import com.voc.system.entity.Action;
import com.voc.system.service.IActionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/02/05 09:35
 */
@Service
public class ActionService implements IActionService {

    @Resource
    private IActionDao actionDao;

    @Override
    public List<Action> findAll() throws BizException {
        return actionDao.findAll();
    }

    @Override
    public boolean add(Action action) {
//        Action ac = actionDao.insert(action);
        return true;
    }
}
