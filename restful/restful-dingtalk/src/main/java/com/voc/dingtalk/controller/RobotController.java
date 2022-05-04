package com.voc.dingtalk.controller;

import com.aliyun.dingtalkoauth2_1_0.Client;
import com.aliyun.dingtalkoauth2_1_0.models.GetAccessTokenRequest;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.voc.dingtalk.service.IRobotService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 钉钉机器人
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/05/01 11:54
 */
@RestController
@RequestMapping("/robot")
public class RobotController {

    @Resource
    private IRobotService robotService;

    /**
     * 使用 Token 初始化账号Client
     *
     * @return Client
     * @throws Exception
     */
    public static Client createClient() throws Exception {
        Config config = new Config();
//        config.protocol = "https";
//        config.setProtocol("https");
//        config.regionId = "central";
        return new com.aliyun.dingtalkoauth2_1_0.Client(config);
    }

    public static void main(String[] args_) throws Exception {
        List<String> args = Arrays.asList(args_);
        Client client = RobotController.createClient();
        GetAccessTokenRequest accessTokenRequest = new GetAccessTokenRequest();
//        accessTokenRequest.setAppKey("");
//        accessTokenRequest.setAppSecret("");
        try {
            client.getAccessToken(accessTokenRequest);
        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
            }

        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
            }

        }
    }
}
