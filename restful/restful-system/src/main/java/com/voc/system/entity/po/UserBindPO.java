package com.voc.system.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.voc.restful.core.entity.BaseEntity;
import com.voc.restful.core.entity.IEntity;
import com.voc.system.constant.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 持久化对象 - 用户绑定
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/08 12:42
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = Table.USER_BIND)
public class UserBindPO extends BaseEntity<String> implements IEntity<String> {

    /**
     * 系统用户ID
     */
    private String uid;

    /**
     * 第三方平台类型
     */
    private String type;

    /**
     * 第三方平台UnionID（通常指第三方账号体系下用户的唯一ID）
     */
    private String unionid;

    /**
     * 第三方平台OpenID（通常指第三方账号体系下某应用中用户的唯一ID）
     */
    private String openid;

}
