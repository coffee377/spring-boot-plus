package com.voc.security.core.third;

import com.voc.common.api.bean.IBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 第三方应用用户信息描述
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/23 15:28
 */
@Deprecated
@Data
@EqualsAndHashCode
public class ThirdApp implements IBean {
    public static AppName DINGTALK = () -> "dingtalk";
    public static AppName ALIPAY = () -> "alipay";
    public static AppName WECHAT = () -> "wechat";
    public static AppName QQ = () -> "qq";

    /**
     * 第三方平台类型
     */
    private AppName type;

    /**
     * 第三方平台UnionID（通常指第三方账号体系下用户的唯一ID）
     */
    private String unionid;

    /**
     * 第三方平台OpenID（通常指第三方账号体系下某应用中用户的唯一ID）
     */
    private String openid;

    private String uid;

    /**
     * 第三方平台用户详细信息
     */
    private Map<String, Object> userInfo;

    public ThirdApp type(String type){
        this.type = () -> type;
        return this;
    }

    public ThirdApp type(AppName type) {
        this.type = type;
        return this;
    }

    public ThirdApp unionid(String unionid) {
        this.unionid = unionid;
        return this;
    }

    public ThirdApp openid(String openid) {
        this.openid = openid;
        return this;
    }

    public ThirdApp userInfo(Map<String, Object> userInfo) {
        this.userInfo = userInfo;
        return this;
    }

    private ThirdApp(AppName type, String unionid, String openid, Map<String, Object> userInfo) {
        this.type = type;
        this.unionid = unionid;
        this.openid = openid;
        this.userInfo = userInfo;
    }

    public static ThirdApp of(AppName type, String unionid, String openid) {
        return new ThirdApp(type, unionid, openid, null);
    }

    public static ThirdApp of(String type, String unionid, String openid) {
        return of(() -> type, unionid, openid);
    }

}
