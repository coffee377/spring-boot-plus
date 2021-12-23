package com.voc.api.utils;

import org.springframework.util.StringUtils;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/10 12:13
 */
public class CommonUtil {

//    /**
//     * 是否自定义登录页面
//     *
//     * @param loginProperties LoginProperties
//     * @return boolean
//     */
//    public static boolean isCustomPage(LoginProperties loginProperties) {
////        return !StringUtils.isEmpty(loginProperties.getPage());
//        return false;
//    }
//
//    /**
//     * 是否默认登录页面
//     *
//     * @param loginProperties LoginProperties
//     * @return boolean
//     */
//    public static boolean isDefaultPage(LoginProperties loginProperties) {
//        return false;
////        return StringUtils.isEmpty(loginProperties.getPage());
//    }

    /**
     * 是否自定义处理
     *
     * @param processUrl LoginProperties
     * @return boolean
     */
    public static boolean isCustomProcessUrl(String processUrl) {
        return StringUtils.hasText(processUrl);
    }

//    /**
//     * 是否默认用户名密码登录处理地址
//     *
//     * @param loginProperties LoginProperties
//     * @return boolean
//     */
//    public static boolean isDefaultProcessUrl(LoginProperties loginProperties) {
//        return StringUtils.isEmpty(loginProperties.getProcessUrl());
//    }

}
