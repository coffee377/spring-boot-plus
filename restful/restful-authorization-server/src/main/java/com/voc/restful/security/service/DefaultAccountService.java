package com.voc.restful.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.voc.common.exception.BizException;
import com.voc.restful.security.core.expection.UnboundUserException;
import com.voc.restful.security.dao.AccountDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/21 11:21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DefaultAccountService implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Override
    public List<Account> accounts() {
        return accountDao.selectList(new QueryWrapper<>());
    }

    @Override
    public Account createByMobile(String mobile) throws BizException {
        Account account = new Account();
        account.setMobile(mobile);
        account.setUsername(mobile);
        accountDao.insert(account);
        return account;
    }

    @Override
    public Account getUserByUsername(String username) {
//        return accountDao.getByUsername(username);
        return null;
    }

    @Override
    public Account getUserByTripartitePlatform(TripartitePlatform platform) throws UnboundUserException {
        return null;
    }

    @Override
    public void resetPassword(String userId) {

    }

    @Override
    public void lock(String userId) {

    }

    @Override
    public void disable(String userId) {

    }

    @Override
    public void enable(String userId) {

    }

    @Override
    public void close(String userId) {

    }

}
