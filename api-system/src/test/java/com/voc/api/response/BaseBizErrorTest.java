package com.voc.api.response;

import org.junit.jupiter.api.Test;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/09 14:54
 */
class BaseBizErrorTest {

    @Test
    void getCode() {
        BaseBizError[] values = BaseBizError.values();
        for (BaseBizError value : values) {
            System.out.println(value.getCode() + " ==== " + value.getMessage());
        }
    }
}
