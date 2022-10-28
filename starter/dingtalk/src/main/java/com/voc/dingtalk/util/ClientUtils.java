package com.voc.dingtalk.util;

import com.aliyun.tea.TeaException;
import com.dingtalk.api.DefaultDingTalkClient;
import com.voc.common.api.biz.BizException;
import com.voc.dingtalk.exception.DingTalkApiException;
import com.voc.dingtalk.url.UrlPath;
import lombok.extern.slf4j.Slf4j;


/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/17 18:54
 */
@Slf4j
public class ClientUtils {

    /**
     * 快速创建旧版客户端
     *
     * @param path 接口路径
     * @return 旧版客户端
     */
    public static DefaultDingTalkClient of(UrlPath path) {
        log.debug("request url：{}", path.get());
        return new DefaultDingTalkClient(path.get());
    }

    /**
     * @param e
     */
    public static void expection(Exception e) throws BizException {
        if (e instanceof TeaException) {
            TeaException teaException = (TeaException) e;
            throw new DingTalkApiException(teaException.code, teaException.message);
        } else {
            throw new DingTalkApiException(e.getMessage());
        }
    }

}
