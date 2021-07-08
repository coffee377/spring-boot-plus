package com.voc.system.entity;

import com.voc.restful.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/08 12:42
 */
@Data
@Document(collection = "sys_social")
@EqualsAndHashCode(callSuper = true)
public class Social extends BaseEntity<String> {
    /**
     * 系统用户ID
     */
    String uid;

    /**
     * 第三方平台类型
     */
    String type;

    /**
     * 第三方平台UnionID（通常指第三方账号体系下用户的唯一ID）
     */
    String unionid;

    /**
     * 第三方平台OpenID（通常指第三方账号体系下某应用中用户的唯一ID）
     */
    String openid;

}
