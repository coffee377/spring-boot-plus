package com.voc.api.utils;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/09 15:10
 */
public class BitUtil {

    /**
     * 两数之和
     *
     * @param a int
     * @param b int
     * @return int
     */
    public static long add(long a, long b) {
        if (b == 0) {
            return a;
        } else {
            /* 进位值 */
            long carry = (a & b) << 1;
            a = a ^ b;
            return add(a, carry);
        }
    }

    /**
     * 两数之差
     *
     * @param a int
     * @param b int
     * @return int
     */
    public static long subtraction(long a, long b) {
        return add(a, ~b + 1);
    }

    /**
     * 两数之积
     *
     * @param a int
     * @param b int
     * @return int
     */
    public static long multiply(long a, long b) {
        long i = 0;
        long res = 0;
        while (b != 0) {
            if ((b & 1) == 1) {
                res += (a << i);
            }
            b = b >> 1;
            i++;
        }
        return res;
    }

    /**
     * 两数之商
     *
     * @param a int
     * @param b int
     * @return int
     */
    public static long division(long a, long b) {
        long res;
        if (a < b) {
            return 0;
        } else {
            res = division(subtraction(a, b), b) + 1;
        }
        return res;
    }
}
