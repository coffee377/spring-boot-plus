package com.voc.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/04 22:01
 */
@RestController
public class LoginPageController {

    @GetMapping("/login")
    public ModelAndView loginPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

}
