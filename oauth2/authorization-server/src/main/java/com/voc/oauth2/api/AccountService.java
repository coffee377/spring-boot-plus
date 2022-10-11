package com.voc.oauth2.api;

import com.voc.common.api.biz.BizException;
import com.voc.common.api.biz.InternalBizStatus;
import com.voc.common.api.dict.enums.UsingStatus;
import com.voc.oauth2.api.model.Account;
import org.springframework.security.oauth2.core.user.OAuth2User;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/06 16:50
 */
public interface AccountService {

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
