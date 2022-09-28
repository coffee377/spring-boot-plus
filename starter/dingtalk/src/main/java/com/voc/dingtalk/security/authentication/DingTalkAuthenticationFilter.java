//package com.voc.dingtalk.security.authentication;
//
//import com.voc.restful.core.third.AppName;
//import com.voc.restful.core.third.ThirdApp;
//import com.voc.restful.security.core.authentication.qrcode.QRAuthenticationFilter;
//
///**
// * @author Wu Yujie
// * @email coffee377@dingtalk.com
// * @time 2021/06/24 21:29
// */
//public class DingTalkAuthenticationFilter extends QRAuthenticationFilter {
//    public static String DEFAULT_LOGIN_PROCESS_URL = "/login/" + ThirdApp.DINGTALK.get();
//
//    public DingTalkAuthenticationFilter() {
//        super(DEFAULT_LOGIN_PROCESS_URL);
//    }
//
//    @Override
//    public AppName getAppType() {
//        return ThirdApp.DINGTALK;
//    }
//}
