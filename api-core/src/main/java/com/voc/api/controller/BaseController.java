package com.voc.api.controller;

import com.voc.api.config.ApiProperties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/24 21:09
 */
public class BaseController {

    @Resource
    public HttpServletRequest request;

    @Resource
    public ApiProperties apiProps;

}
