package com.voc.api.utils;

import com.voc.restful.core.props.LoginProperties;
import org.springframework.util.StringUtils;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/10 12:13
 */
public class LoginUtil {

    /**
     * 是否自定义登录页面
     *
     * @param loginProperties LoginProperties
     * @return boolean
     */
    public static boolean isCustomPage(LoginProperties loginProperties) {
        return !StringUtils.isEmpty(loginProperties.getPage());
    }

    /**
     * 是否默认登录页面
     *
     * @param loginProperties LoginProperties
     * @return boolean
     */
    public static boolean isDefaultPage(LoginProperties loginProperties) {
        return StringUtils.isEmpty(loginProperties.getPage());
    }

    /**
     * 是否自定义用户名密码登录处理地址
     *
     * @param loginProperties LoginProperties
     * @return boolean
     */
    public static boolean isCustomProcessUrl(LoginProperties loginProperties) {
        return !StringUtils.isEmpty(loginProperties.getProcessUrl());
    }

    /**
     * 是否默认用户名密码登录处理地址
     *
     * @param loginProperties LoginProperties
     * @return boolean
     */
    public static boolean isDefaultProcessUrl(LoginProperties loginProperties) {
        return StringUtils.isEmpty(loginProperties.getProcessUrl());
    }

}
