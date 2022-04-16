package com.voc.system.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.voc.api.enums.UsingStatus;
import com.voc.restful.core.entity.IEntity;
import com.voc.restful.core.entity.impl.BaseUser;
import com.voc.restful.core.persist.PO;
import com.voc.system.constant.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 持久化对象 - 用户
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/19 18:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = Table.USER)
@TableName(Table.USER)
public class UserPO extends BaseUser<String> implements IEntity<String> {

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

    /**
     * 用户状态
     */
    private UsingStatus status;

    @Override
    public boolean isAccountNonLocked() {
        return !UsingStatus.LOCK.equals(status);
    }

    @Override
    public boolean isEnabled() {
        return UsingStatus.NORMAL.equals(status);
    }
}
