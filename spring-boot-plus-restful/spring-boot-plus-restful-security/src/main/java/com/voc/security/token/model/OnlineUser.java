package com.voc.security.token.model;

import lombok.Data;

import java.time.Instant;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/18 15:18
 */
@Data
public class OnlineUser implements Comparable<OnlineUser> {

    /**
     * 用户唯一标识
     */
    private String uid;

    /**
     * 上线时间，即用户 Token 签发时间
     */
    private Instant issuedAt;

    @Override
    public int compareTo(OnlineUser o) {
        int i1 = uid.compareTo(o.uid);
        int i2 = issuedAt.compareTo(o.issuedAt);
        return i1 + i2;
    }

}
