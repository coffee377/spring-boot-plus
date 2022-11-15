package com.voc.security.oauth2.endpoint;

import com.voc.security.oauth2.entity.po.OAuth2Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/06/11 21:45
 */
@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {

//    @Resource
//    private AccountService accountService;

// ConverterFactory FormatterRegistry
//    @PostMapping("/upload")
//    public String upload(@RequestBody OAuth2Client client) {
//        log.warn("{}", client);
//        return "account";
//    }

    @GetMapping
    public List<Object> query() {
//        return accountService.accounts();
        return Arrays.asList(1, 2, 3, 4, 5, 6);
    }

    @PostMapping
    public OAuth2Account create(String mobile, String code) {
//        return accountService.createByMobile(mobile);
        return null;
    }

    /**
     * 重置密码
     *
     * @param username 用户名
     */
    @PutMapping("/{username}/password/reset")
    public void resetPassword(@PathVariable("username") String username) {
//        accountService.resetPassword(userId);
    }

    /**
     * 锁定账号
     *
     * @param username 用户名
     */
    @PutMapping("/{username}/lock")
    public void lock(@PathVariable("username") String username) {
//        accountService.lock(userId);
    }

    /**
     * 禁用账号
     *
     * @param username 用户名
     */
    @PutMapping("/{username}/disable")
    public void disable(@PathVariable("username") String username) {
//        accountService.disable(userId);
    }

    /**
     * 启用账号
     *
     * @param username 用户名
     */
    @PutMapping("/{username}/enable")
    public void enable(@PathVariable("username") String username) {
//        accountService.enable(userId);
    }

    /**
     * 注销账号
     *
     * @param username 用户名
     */
    @DeleteMapping("/{username}")
    public void close(@PathVariable("username") String username) {
//        accountService.close(userId);
    }
}
