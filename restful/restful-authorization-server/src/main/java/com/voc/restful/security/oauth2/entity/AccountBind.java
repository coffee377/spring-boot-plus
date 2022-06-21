package com.voc.restful.security.oauth2.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.voc.restful.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/21 13:26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "oauth2_account_bind")
public class AccountBind extends BaseEntity<String> {

    /**
     * 账号 ID
     */
    private String accountId;

    /**
     * 第三方平台名称
     */
    private String name;

    /**
     * 用户在第三方平台唯一 ID
     */
    private String unionId;

    /**
     * 用户在第三方平台应用的开放 ID
     */
    private String openId;

}
