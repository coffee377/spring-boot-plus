package com.voc.security.oauth2.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.voc.common.api.entity.impl.BasePersistEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/11/08 15:16
 */
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@TableName(value = "oauth2_authorization")
public class Oauth2Authorization extends BasePersistEntity<String> {

    /**
     * 注册的客户端 ID
     */
    private String registeredClientId;

    /**
     * 授权主体名称
     */
    private String principalName;

    /**
     * 授权类型
     */
    private String authorizationGrantType;

    /**
     * 实际授予的权限
     */
    private String authorizedScopes;

    /**
     *
     */
    private String attributes;

}
