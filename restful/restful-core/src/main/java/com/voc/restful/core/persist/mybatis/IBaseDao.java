package com.voc.restful.core.persist.mybatis;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.voc.restful.core.persist.entity.PersistEntity;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/17 00:53
 */
public interface IBaseDao<P extends PersistEntity> extends BaseMapper<P> {
}
