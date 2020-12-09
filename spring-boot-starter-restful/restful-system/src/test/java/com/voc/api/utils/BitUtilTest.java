package com.voc.api.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/09 15:27
 */
class BitUtilTest {

    @Test
    void add() {
        assertEquals(3, BitUtil.add(1, 2));
        assertEquals(108, BitUtil.add(100, 8));
    }

    @Test
    void subtraction() {
        assertEquals(-1, BitUtil.subtraction(1, 2));
        assertEquals(92, BitUtil.subtraction(100, 8));
    }

    @Test
    void multiply() {
        assertEquals(1000, BitUtil.multiply(1, 1000));
    }

    @Test
    void division() {
    }
}
