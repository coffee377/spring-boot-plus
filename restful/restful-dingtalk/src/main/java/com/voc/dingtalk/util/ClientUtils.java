package com.voc.dingtalk.util;

import com.dingtalk.api.DefaultDingTalkClient;
import com.voc.dingtalk.url.UrlPath;


/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/17 18:54
 */
public class ClientUtils {

    /**
     * 快速创建旧版客户端
     *
     * @param path 接口路径
     * @return 旧版客户端
     */
    public static DefaultDingTalkClient of(UrlPath path) {
        return new DefaultDingTalkClient(path.get());
    }

}
