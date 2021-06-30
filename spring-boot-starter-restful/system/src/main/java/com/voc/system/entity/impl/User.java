package com.voc.system.entity.impl;

import com.voc.restful.core.entity.impl.BaseUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/19 18:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "sys_user")
public class User extends BaseUser<String> {
    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 用户头像
     */
    private String avatar;

}
