package com.voc.system.entity.impl;

import com.voc.restful.core.entity.BaseEntity;
import com.voc.system.entity.IRole;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/19 18:48
 */
@Getter
@Setter
public class Role extends BaseEntity<String> implements IRole<String> {

    private String code;

    private String name;

}
