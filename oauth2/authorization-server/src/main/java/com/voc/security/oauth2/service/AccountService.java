package com.voc.security.oauth2.service;

import com.voc.common.api.biz.BizException;
import com.voc.common.api.biz.InternalBizStatus;
import com.voc.common.api.dict.enums.UsingStatus;
import com.voc.security.oauth2.entity.Account;
import com.voc.security.oauth2.entity.TripartitePlatform;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.oauth2.core.user.OAuth2User;

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

    /**
     * 密码转换
     *
     * @param password  原始密码
     * @param plaintext 是否明文
     * @return 加密处理后
     */
    default String passwordConverter(String password, boolean plaintext) {
        return password;
    }

    /**
     * 创建账号
     *
     * @param account       账号信息
     * @param plainPassword 是否存储约定格式明文密码(主要方便测试)
     * @return boolean
     */
    Account create(Account account, boolean plainPassword);

    /**
     * 创建账号
     *
     * @return 账号信息
     */
    default Account createAccount(OAuth2User user) {
        // 根据 OAuth2User 创建系统账号，并进行账号绑定
        throw new BizException(InternalBizStatus.UN_IMPLEMENTED_METHOD);
    }

    /**
     * 更新账号状态
     *
     * @param status 账号状态
     */
    void updateStatus(UsingStatus status);
}
