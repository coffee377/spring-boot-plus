package com.voc.demo.service.impl;

import com.voc.demo.service.IDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/29 10:34
 */
@Slf4j
@Service
public class DemoService implements IDemoService {
    @Override
    public String hello(String content) {
        log.warn("start...");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            log.error("{}", e.getMessage());
        }
        log.warn("end...");
        return String.format("Hello %s", content);
    }
}
