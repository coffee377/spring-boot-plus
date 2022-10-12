package com.voc.system.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.voc.persist.PersistEntity;
import com.voc.system.constant.Table;
import lombok.Data;

/**
 * 持久化对象 - 用户
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/19 18:10
 */
@Data
@TableName(value = Table.USER)
public class UserPO implements PersistEntity<String> {
    private String id;
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
