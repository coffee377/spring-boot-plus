package com.voc.security.oauth2.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.voc.security.oauth2.entity.po.OAuth2Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/11/08 15:09
 */
@Mapper
public interface OAuth2AccountRepository extends BaseMapper<OAuth2Account> {
}
