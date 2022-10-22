package com.voc.security.oauth2.entity;

import com.voc.common.api.bean.IBean;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.oauth2.core.user.OAuth2User;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/06/11 19:36
 */
@Deprecated
@Getter
@EqualsAndHashCode
public class TripartitePlatform implements IBean {
    public static TripartitePlatform DINGTALK = new TripartitePlatform("dingtalk");
    public static TripartitePlatform ALIPAY = new TripartitePlatform("alipay");
    public static TripartitePlatform WECHAT = new TripartitePlatform("wechat");
    public static TripartitePlatform QQ = new TripartitePlatform("qq");
    public static TripartitePlatform JQ = new TripartitePlatform("jqsoft");

    /**
     * 第三方平台名称
     */
    private final String name;

    /**
     * 用户在第三方平台的唯一标识
     */
    private String unionId;

    /**
     * 用户在第三方平台某一具体应用中唯一标识
     */
    private String openId;

    private OAuth2User userInfo;

    public TripartitePlatform(String name) {
        this.name = name;
    }

    public TripartitePlatform unionId(String unionId) {
        this.unionId = unionId;
        return this;
    }

    public void openId(String openId) {
        this.openId = openId;
    }

    public void userInfo(OAuth2User info) {
        this.userInfo = info;
    }
}
