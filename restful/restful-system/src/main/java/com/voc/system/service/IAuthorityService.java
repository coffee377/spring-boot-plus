package com.voc.system.service;

import com.voc.system.entity.enums.AuthorityType;
import com.voc.system.entity.IMask;

import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/16 17:41
 */
public interface IAuthorityService {

    /**
     * 获取用户拥有的所有权限
     *
     * @param username 用户名
     * @return List<IMask>
     */
    List<IMask> getAll(String username);

    /**
     * @param type
     * @return
     */
    List<IMask> getByType(String username, AuthorityType type);
}
