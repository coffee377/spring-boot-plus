package com.voc.system.constant;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/15 09:23
 */
public interface Table {
    String USER = "sys_user";
    String ROLE = "sys_role";
    String AUTHORITY = "sys_authority";
    String AUTHORITIES = "sys_authorities";

    String USER_BIND = "sys_user_bind";

    String USE_ROLE_RELATION = "sys_user_role";
    String ROLE_AUTHORITY_RELATION = "sys_role_authority";


    /**
     * 菜单权限
     */
    String MENU = "sys_menu";
    String COMPONENT = "sys_component";
    String API = "sys_api";
    String OPERATION = "sys_operation";
    String DATA = "sys_data";

}
