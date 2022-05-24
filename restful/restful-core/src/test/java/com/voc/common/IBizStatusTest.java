package com.voc.common;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/05/24 23:26
 */
@Slf4j
class IBizStatusTest {

    @Test
    void getCode() {
        int code = 1;

        int lc = 1 >> 3;
        log.warn("{} => {}", code, lc);
    }
}
