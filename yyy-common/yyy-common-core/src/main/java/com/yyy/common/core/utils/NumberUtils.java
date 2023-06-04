package com.yyy.common.core.utils;

/**
 * @Author 3H
 * @Date 2020/3/24 16:34
 * @Description TODO
 */
public class NumberUtils {

    /**
     * 取出一个指定长度大小的随机正整数.
     * @param length
     * @return  int
     *
     */
    public static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }
}
