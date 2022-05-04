package com.voc.restful.security.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/21 13:10
 */
@Mapper
public interface AuthUserDao extends BaseMapper<String> {
}
