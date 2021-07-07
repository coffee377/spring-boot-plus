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
    private String username;
    private String realName;
    private String avatar;

//    private List<String> auty;

    private List<Menu> menus;
}
