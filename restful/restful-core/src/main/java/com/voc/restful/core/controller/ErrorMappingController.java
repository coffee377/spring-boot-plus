package com.voc.restful.core.controller;

import com.voc.common.api.biz.InternalBizStatus;
import com.voc.restful.core.vo.ErrorMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/03/26 22:45
 */
@RestController
@RequestMapping("/error/code")
public class ErrorMappingController {

    /**
     * 获取当前系统错误编码对照表
     * @return List<ErrorMapping>
     */
    @GetMapping
    public List<ErrorMapping> mapping(){
        return Arrays.stream(InternalBizStatus.values()).map(baseBizStatus -> new ErrorMapping(baseBizStatus.getCode(), baseBizStatus.getMessage(), baseBizStatus.getHttpStatus())).collect(Collectors.toList());
    }
}
