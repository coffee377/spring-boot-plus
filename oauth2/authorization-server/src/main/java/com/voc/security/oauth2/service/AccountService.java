package com.voc.security.oauth2.service;

import com.voc.common.api.biz.BizException;
import com.voc.common.api.biz.InternalBizStatus;
import com.voc.common.api.dict.enums.UsingStatus;
import com.voc.security.oauth2.entity.Account;
import com.voc.security.oauth2.entity.TripartitePlatform;
import com.voc.security.oauth2.entity.dto.AccountDTO;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import org.springframework.security.oauth2.core.user.OAuth2User;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/06/11 19:24
 */
public interface AccountService {

    Page<Account> page(Integer page, Integer size);

    /**
     * 创建账号
     *
     * @param account       账号信息
     * @param plainPassword 是否存储约定格式明文密码(主要方便测试)
     * @return boolean
     */
    Account create(AccountDTO account, boolean plainPassword);

    /**
     * 根据 {@link OAuth2User} 创建账号
     *
     * @return 账号信息
     */
    default Account create(OAuth2User user) {
        // 根据 OAuth2User 创建系统账号，并进行账号绑定
        throw new BizException(InternalBizStatus.UN_IMPLEMENTED_METHOD);
    }

    /**
     * 根据手机号创建账号
     *
     * @return Account
     */
    Account createByMobile(@NonNull String mobile, @NonNull String code) throws BizException;

    /**
     * 根据 ID 删除用户
     *
     * @param accountId 账号 ID
     */
    void deleteById(String accountId);

    void update();

    /**
     * 根据用户名获取账号信息
     *
     * @param username 用户名
     * @return Account
     */
    Account findByUsername(String username);

    /**
     * 获取与第三平台用户绑定的
     *
     * @param platform 用户在三方平台的信息
     * @return 账户信息
     */
    Account findByOAuth2Client(TripartitePlatform platform);

    /**
     * 重置密码
     *
     * @param accountId 账号 ID
     */
    void resetPassword(String accountId);

    /**
     * 锁定账号
     *
     * @param accountId 账号 ID
     */
    void lock(String accountId);

    /**
     * 禁用账号
     *
     * @param accountId 账号 ID
     */
    void disable(String accountId);

    /**
     * 启用账号
     *
     * @param accountId 账号 ID
     */
    void enable(String accountId);

    void close(String accountId);

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
     * 更新账号状态
     *
     * @param status 账号状态
     */
    void updateStatus(UsingStatus status);
}
