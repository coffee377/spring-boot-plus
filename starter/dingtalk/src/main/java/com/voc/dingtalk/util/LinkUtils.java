package com.voc.dingtalk.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/22 09:47
 */
@Slf4j
public class LinkUtils {
    public static final String DINGTALK_PROTOCOL = "dingtalk://dingtalkclient/page/link";

    /**
     * 获取钉钉容器协议链接地址
     * <a href="https://open.dingtalk.com/document/orgapp-client/unified-routing-protocol">统一跳转协议</a>
     *
     * @param origin       实际跳转地址（可包含格式化参数）
     * @param isPC         是否 PC 端
     * @param uriVariables 参数变量
     * @return 协议地址
     */
    public static String dingtalkProtocolUrl(String origin, boolean isPC, Object... uriVariables) {
        UriComponentsBuilder builder = getUriComponentsBuilder(isPC);
        String expandUrl = UriComponentsBuilder.fromHttpUrl(origin).buildAndExpand(uriVariables).toUriString();
        builder.queryParam("url", expandUrl);
        return builder.encode().toUriString();
    }

    /**
     * 获取钉钉容器协议链接地址
     *
     * @param origin       实际跳转地址（可包含格式化参数）
     * @param isPC         是否 PC 端
     * @param uriVariables 参数变量
     * @return 协议地址
     */
    public static String dingtalkProtocolUrl(String origin, boolean isPC, Map<String, ?> uriVariables) {
        UriComponentsBuilder builder = getUriComponentsBuilder(isPC);
        String expandUrl = UriComponentsBuilder.fromHttpUrl(origin).buildAndExpand(uriVariables).toUriString();
        builder.queryParam("url", expandUrl);
        return builder.encode().toUriString();
    }

    private static UriComponentsBuilder getUriComponentsBuilder(boolean isPC) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(DINGTALK_PROTOCOL);
        if (isPC) {
            builder.queryParam("pc_slide", true);
        }
        return builder;
    }

    /**
     * @param url          相对或绝对地址（包含参数）
     * @param endpoint     url相对地址时的主机地址
     * @param uriVariables 变量参数
     * @return 完整 url
     */
    public static String url(@NonNull String url, @Nullable String endpoint, Object... uriVariables) {
        return arrayTemplateUrl(url, endpoint, false, uriVariables);
    }

    /**
     * @param url          相对或绝对地址（包含参数）
     * @param endpoint     url相对地址时的主机地址
     * @param uriVariables 变量参数
     * @return 完整 url
     */
    public static String url(@NonNull String url, @Nullable String endpoint, Map<String, ?> uriVariables) {
        return mapTemplateUrl(url, endpoint, false, uriVariables);
    }

    /**
     * @param url          相对或绝对地址吧（包含参数）
     * @param endpoint     url相对地址时的主机地址
     * @param uriVariables 变量参数
     * @return 完整 url
     */
    public static String encodeUrl(@NonNull String url, @Nullable String endpoint, Object... uriVariables) {
        return arrayTemplateUrl(url, endpoint, true, uriVariables);
    }

    public static String encodeUrl(@NonNull String url, @Nullable String endpoint, Map<String, ?> uriVariables) {
        return mapTemplateUrl(url, endpoint, true, uriVariables);
    }

    private static UriComponentsBuilder getUriComponentsBuilder(String url, String endpoint, boolean encode) {
        Assert.hasText(url, "endpoint must has text");
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
        if (encode) builder.encode();
        UriComponents components = builder.build();
        if (!StringUtils.hasText(components.getHost())) {
            Assert.hasText(endpoint, "endpoint must has text");
            assert endpoint != null;
            builder = UriComponentsBuilder.fromHttpUrl(endpoint).uriComponents(components);
            if (encode) builder.encode();
        }
        return builder;
    }

    private static String arrayTemplateUrl(@NonNull String url, @Nullable String endpoint, boolean encode, Object... uriVariables) {
        UriComponentsBuilder builder = getUriComponentsBuilder(url, endpoint, encode);
        UriComponents components = builder.buildAndExpand(uriVariables);
        return components.toUriString();
    }

    /**
     * @param url          相对或绝对地址吧（包含参数）
     * @param endpoint     url相对地址时的主机地址
     * @param uriVariables 变量参数
     * @return 完整 url
     */
    public static String mapTemplateUrl(@NonNull String url, @Nullable String endpoint, boolean encode, Map<String, ?> uriVariables) {
        UriComponentsBuilder builder = getUriComponentsBuilder(url, endpoint, encode);
        UriComponents components = builder.buildAndExpand(uriVariables);
        return components.toUriString();
    }
}
