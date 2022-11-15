package com.voc.security.oauth2.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.voc.common.api.entity.impl.BaseEntity;
import com.voc.common.api.entity.impl.BasePersistEntity;
import com.voc.security.core.authentication.AuthProvider;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/21 13:26
 */
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@TableName(value = "oauth2_provider_account")
public class OAuth2ProviderAccount extends BasePersistEntity<String> {

    /**
     * 账号 ID
     */
    private String accountId;

    /**
     * 认证提供商
     */
    private AuthProvider provider;

    /**
     * 认证提供商内应用 ID
     */
    private String clientId;

    /**
     * 用户在认证提供商的唯一 ID
     */
    private String unionId;

    /**
     * 用户在应用内唯一 ID
     */
    private String openId;

}
