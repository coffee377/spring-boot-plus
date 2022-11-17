package com.voc.dingtalk;

import com.voc.dingtalk.service.AppService;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/11/16 08:59
 */
public class DingTalkRestTemplateCustomizer implements RestTemplateCustomizer {
    private final AppService appService;

    public DingTalkRestTemplateCustomizer(AppService appService) {
        this.appService = appService;
    }

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setInterceptors(Collections.singletonList(new DingTalkClientHttpRequestInterceptor(appService)));
    }

    static class DingTalkClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {
        private final AppService appService;

        public DingTalkClientHttpRequestInterceptor(AppService appService) {
            this.appService = appService;
        }

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            HttpHeaders headers = request.getHeaders();
            if (!MediaType.APPLICATION_JSON.equals(headers.getContentType())) {
                headers.setContentType(MediaType.APPLICATION_JSON);
            }

            /* 协同办公应用的访问令牌 */
            String accessToken = appService.getAccessToken("teamwork");
            headers.set("x-acs-dingtalk-access-token", accessToken);

            return execution.execute(request, body);
        }
    }

}
