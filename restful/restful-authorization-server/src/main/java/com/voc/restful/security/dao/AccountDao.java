package com.voc.restful.security.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.voc.restful.security.service.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/21 13:10
 */
@Mapper
public interface AccountDao extends BaseMapper<Account> {


//    /**
//     * 根据用户名获取账号信息
//     *
//     * @param username 用户名
//     * @return 账号信息
//     */
//    Account getByUsername(String username);

//    /**
//     * 根据 用户名/手机/邮箱 获取账号信息
//     * @param key 用户名/手机/邮箱
//     * @return 账号信息
//     */
//    Account getByUsernameOrMobileOrEmail(String key);
}
