package com.voc.system.vo;

import com.voc.system.entity.impl.Menu;
import lombok.Data;

import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/07 18:28
 */
@Data
//@EqualsAndHashCode(callSuper = true)
public class UserVO {
    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 头像
     */
    private String avatar;

    private List<Menu> menus;
}
