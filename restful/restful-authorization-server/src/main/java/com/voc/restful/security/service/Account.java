package com.voc.restful.security.service;

import com.baomidou.mybatisplus.annotation.TableName;
import com.voc.restful.core.entity.IUser;
import com.voc.restful.core.entity.impl.BaseUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/21 13:26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "oauth2_account", excludeProperty = "authorities")
public class Account extends BaseUser<String> implements IUser<String> {

    private String mobile;
}
