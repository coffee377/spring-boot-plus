//package com.voc.security.oauth2;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//
///**
// * @author Wu Yujie
// * @email coffee377@dingtalk.com
// * @time 2021/06/21 23:39
// */
//@ConfigurationProperties(prefix = "api.security.oauth2")
//public class OAuth2Properties {
//
//    /**
//     * 登录入口的基础路径path
//     */
//    private String authorizationRequestBaseUri = "/oauth2/authorization";
//
//    /**
//     * 回调地址的基础路径path
//     */
//    private String authorizationResponseBasePath = "/login/oauth2/code";
//
//    public String getAuthorizationRequestBaseUri() {
//        return authorizationRequestBaseUri;
//    }
//
//    public void setAuthorizationRequestBaseUri(String authorizationRequestBaseUri) {
//        this.authorizationRequestBaseUri = authorizationRequestBaseUri;
//    }
//
//    public String getAuthorizationResponseBasePath() {
//        return authorizationResponseBasePath;
//    }
//
//    /**
//     * 如果配置的路径后面有"/"，则去除
//     *
//     * @param authorizationResponseBasePath oauth2回调地址前缀
//     */
//    public void setAuthorizationResponseBasePath(String authorizationResponseBasePath) {
//        if (authorizationResponseBasePath.endsWith("/")) {
//            authorizationResponseBasePath = authorizationResponseBasePath.substring(0, authorizationResponseBasePath.length() - 2);
//        }
//        this.authorizationResponseBasePath = authorizationResponseBasePath;
//    }
//
//    public String getAuthorizationResponseBaseUri() {
//        return authorizationResponseBasePath + "/*";
//    }
//
//}
