package com.voc.dingtalk.client;

import com.aliyun.dingtalkoauth2_1_0.Client;
import com.aliyun.teaopenapi.models.Config;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/28 15:32
 */
public class OAuth2Client extends Client {

    public OAuth2Client(Config config) throws Exception {
        super(config);
    }
}
