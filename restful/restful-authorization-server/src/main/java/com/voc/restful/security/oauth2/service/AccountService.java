package com.voc.restful.security.oauth2.service;

import com.voc.common.exception.BizException;
import com.voc.restful.security.oauth2.entity.Account;
import com.voc.restful.security.oauth2.entity.TripartitePlatform;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/06/11 19:24
 */
public interface AccountService {

    List<Account> accounts();

    /**
     * 根据手机号创建账号
     * @return Account
     */
    Account createByMobile(@Param("mobile") String mobile) throws BizException;

    /**
     * 根据用户名获取账号信息
     * @param username 用户名
     * @return Account
     */
    Account getUserByUsername(String username);

    /**
     * 获取与第三平台用户绑定的
     * @param platform 用户在三方平台的信息
     * @return 账户信息
     */
    Account getUserByTripartitePlatform(TripartitePlatform platform);

    void resetPassword(String userId);

    void lock(String userId);

    void disable(String userId);

    void enable(String userId);

    void close(String userId);

}
