package com.voc.dingtalk.url;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/07/16 15:20
 */
@Getter
@AllArgsConstructor
public enum Notification implements UrlPath {
    WORD_SEND_MESSAGE("/topapi/message/corpconversation/asyncsend_v2"),
    ;

    @Override
    public String getPath() {
        return path;
    }

    private final String path;
}
