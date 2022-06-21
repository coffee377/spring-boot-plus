package com.voc.restful.security.oauth2.controller;

import com.voc.restful.security.oauth2.entity.Account;
import com.voc.restful.security.oauth2.entity.OAuth2Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/upload")
    public String upload(@RequestBody OAuth2Client client) {
        log.warn("{}", client);
        return "account";
    }

    @GetMapping
    public List<Account> query() {
//        return accountService.accounts();
        return null;
    }

    @PostMapping
    public Account create(String mobile) {
//        return accountService.createByMobile(mobile);
        return null;
    }

    /**
     * 重置密码
     *
     * @param userId 用户 ID
     */
    @PutMapping("/{uid}/password/reset")
    public void resetPassword(@PathVariable("uid") String userId) {
//        accountService.resetPassword(userId);
    }

    /**
     * 锁定账号
     *
     * @param userId 用户 ID
     */
    @PutMapping("/{uid}/lock")
    public void lock(@PathVariable("uid") String userId) {
//        accountService.lock(userId);
    }

    /**
     * 禁用账号
     *
     * @param userId 用户 ID
     */
    @PutMapping("/{uid}/disable")
    public void disable(@PathVariable("uid") String userId) {
//        accountService.disable(userId);
    }

    /**
     * 禁用账号
     *
     * @param userId 用户 ID
     */
    @PutMapping("/{uid}/enable")
    public void enable(@PathVariable("uid") String userId) {
//        accountService.enable(userId);
    }

    /**
     * 注销账号
     *
     * @param userId 用户 ID
     */
    @DeleteMapping("/{uid}")
    public void close(@PathVariable("uid") String userId) {
//        accountService.close(userId);
    }
}
