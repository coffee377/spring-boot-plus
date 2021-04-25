package com.voc.system.controller;

import com.voc.restful.core.response.BaseBizStatus;
import com.voc.restful.core.response.BizException;
import com.voc.system.entity.Account;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/04/25 12:01
 */
@RestController
public class UserController {

    @PostMapping("/login")
    public String login(@RequestBody Account account) {
        String username = account.getUsername();
        String password = account.getPassword();
        if (!StringUtils.hasText(username)) {
            throw new BizException(1234,"用户名不能为空");
        }
        if (!password.equals("123456")) {
            throw new BizException(BaseBizStatus.BAD_CREDENTIALS);
        }
        return "ABC";
    }
}
