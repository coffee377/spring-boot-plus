package com.voc.system.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.voc.restful.core.entity.IUser;
import com.voc.restful.core.entity.impl.BaseUser;
import com.voc.persist.PersistEntity;
import com.voc.system.constant.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 持久化对象 - 用户
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/19 18:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = Table.USER)
public class UserPO extends BaseUser<String> implements IUser<String>, PersistEntity<String> {

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
