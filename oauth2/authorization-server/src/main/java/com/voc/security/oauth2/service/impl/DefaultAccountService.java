//package com.voc.security.oauth2.service.impl;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.voc.common.api.biz.BizException;
//import com.voc.security.core.expection.UnboundUserException;
//import com.voc.security.oauth2.dao.AccountDao;
//import com.voc.security.oauth2.entity.Account;
//import com.voc.security.oauth2.entity.TripartitePlatform;
//import com.voc.security.oauth2.service.AccountService;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * @author Wu Yujie
// * @email coffee377@dingtalk.com
// * @time 2022/04/21 11:21
// */
//@Service
//@Transactional(rollbackFor = Exception.class)
//public class DefaultAccountService implements AccountService {
//
//    @Resource
//    private AccountDao accountDao;
//
//    @Override
//    public List<Account> accounts() {
//        return accountDao.selectList(new QueryWrapper<>());
//    }
//
//    @Override
//    public Account createByMobile(String mobile) throws BizException {
//        Account account = new Account();
//        account.setMobile(mobile);
//        account.setUsername(mobile);
//        accountDao.insert(account);
//        return account;
//    }
//
//    @Override
//    public Account getUserByUsername(String username) {
////        return accountDao.getByUsername(username);
//        return null;
//    }
//
//    @Override
//    public Account getUserByTripartitePlatform(TripartitePlatform platform) throws UnboundUserException {
//        return null;
//    }
//
//    @Override
//    public void resetPassword(String userId) {
//
//    }
//
//    @Override
//    public void lock(String userId) {
//
//    }
//
//    @Override
//    public void disable(String userId) {
//
//    }
//
//    @Override
//    public void enable(String userId) {
//
//    }
//
//    @Override
//    public void close(String userId) {
//
//    }
//
//}
