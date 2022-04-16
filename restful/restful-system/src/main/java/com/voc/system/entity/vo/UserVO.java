package com.voc.system.entity.vo;

import com.voc.restful.core.vo.JsonVO;
import com.voc.system.entity.vo.common.KeyName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/07 18:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserVO extends JsonVO {
    /**
     * 用户唯一标识
     */
    private String uid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 主页地址
     */
    private String homePath;

    /**
     * 用户描述
     */
    private String desc;

    /**
     * 用户标签
     */
    private List<KeyName> tags;
}
