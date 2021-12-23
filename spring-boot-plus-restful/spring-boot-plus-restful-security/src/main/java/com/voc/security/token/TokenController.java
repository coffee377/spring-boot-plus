package com.voc.security.token;

import com.voc.restful.core.response.ResponseResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/16 18:53
 */
@RestController
@RequestMapping("/token")
public class TokenController {

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private TokenGenerator tokenGenerator;

    @Resource
    private TokenStorage tokenStorage;

    @GetMapping("/verify")
    @ResponseResult(false)
    public String verifyToken() {
        return "111111";
    }

    /**
     * 刷新当前用户过期的令牌
     *
     * @return 新的访问令牌
     */
    @PreAuthorize("hasAuthority('SCOPE_refresh_token')")
    @GetMapping("/refresh")
    public String refreshToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return tokenGenerator.refresh(userDetailsService, authentication);
    }

    /**
     * 下线所有用户
     * 拥有踢人角色才可访问
     */
    @PreAuthorize("hasRole('KICKING')")
    @GetMapping("/offline")
    public void offlineAll() {
        tokenStorage.offlineAll();
    }

    /**
     * 拥有踢人角色才可访问
     *
     * @param uid 用户唯一标识
     */
    @PreAuthorize("hasRole('KICKING')")
    @GetMapping("/offline/{uid}")
    public void offline(@PathVariable String uid) {
        tokenStorage.offline(uid);
    }

    @PreAuthorize("hasRole('KICKING')")
    @PostMapping("/offline/{uid}")
    public void offline2(@PathVariable String uid, @RequestBody List<Instant> issuedAt) {
        Optional.ofNullable(issuedAt)
                .orElse(Collections.emptyList())
                .forEach(instant -> tokenStorage.offline(uid, instant));

    }
}
