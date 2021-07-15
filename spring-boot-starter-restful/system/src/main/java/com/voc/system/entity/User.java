package com.voc.system.entity;

import com.voc.restful.core.entity.impl.BaseUser;
import com.voc.system.constant.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 系统用户
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/19 18:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = TableName.USER)
public class User extends BaseUser<String> {
    /**
     * 工号
     */
    private String jobNumber;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 用户头像
     */
    private String avatar;

}
