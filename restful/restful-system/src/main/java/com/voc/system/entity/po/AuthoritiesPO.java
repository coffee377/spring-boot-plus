package com.voc.system.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.voc.common.api.entity.IEntity;
import com.voc.restful.core.entity.BaseEntity;
import com.voc.system.constant.Table;
import com.voc.system.entity.IPersistAuthorities;
import com.voc.system.entity.enums.AuthorityType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;

/**
 * 持久化对象 - 权限
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/14 15:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = Table.AUTHORITIES)
public class AuthoritiesPO extends BaseEntity<String> implements IEntity<String>, IPersistAuthorities {

    /**
     * 权限类型
     */
    AuthorityType type;

    /**
     * 相关父级资源 id
     */
    private String parentResourceId;

    /**
     * 权限大整数(十进制)
     */
    private String authorities;

    @Override
    public BigInteger get() {
        return new BigInteger(authorities);
    }

}
