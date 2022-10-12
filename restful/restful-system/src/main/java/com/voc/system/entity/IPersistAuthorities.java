package com.voc.system.entity;

import com.voc.common.api.authority.IAuthorities;
import com.voc.system.entity.enums.AuthorityType;

/**
 * 权限集合
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/16 11:38
 */
public interface IPersistAuthorities extends IAuthorities {

    /**
     * 权限类型
     *
     * @return AuthorityType
     */
    AuthorityType getType();
}
