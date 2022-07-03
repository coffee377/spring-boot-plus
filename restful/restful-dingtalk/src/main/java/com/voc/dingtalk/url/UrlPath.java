package com.voc.dingtalk.url;

import org.springframework.web.util.UriComponentsBuilder;

import java.util.function.Supplier;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/07/03 10:10
 */
public interface UrlPath extends Supplier<String> {

    /**
     * @return 接口路径
     */
    String getPath();

    @Override
    default String get() {
        return UriComponentsBuilder.fromUriString("https://oapi.dingtalk.com").path(getPath()).build().toUriString();
    }
}
