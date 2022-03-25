package com.voc.dingtalk.util;

import com.dingtalk.api.DefaultDingTalkClient;
import com.voc.dingtalk.UrlConst;


/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/17 18:54
 */
public class ClientUtils {

    public static DefaultDingTalkClient of(UrlConst url) {
        return new DefaultDingTalkClient("https://oapi.dingtalk.com" + url.getUrl());
    }

}
