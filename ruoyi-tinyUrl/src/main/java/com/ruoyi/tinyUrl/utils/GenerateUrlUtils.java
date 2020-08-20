package com.ruoyi.tinyUrl.utils;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

public class GenerateUrlUtils {

    private static char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'
            , 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a',
            'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p'
            , 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static int radio = 62;

    /**
     * 单线程执行此方法
     * 根据时间戳将其转化为62进制，固定长度为7位，
     * 根据62位产生一个随机数，替换
     * 对于时间戳来说，前几位不经常变化，所以替换前3位为以为
     *
     * @return
     */
    public synchronized static String generateUrl() {
        String encode = encode();
        int randomO = RandomUtils.nextInt(0, 61);
        String substring = StringUtils.substring(encode, 3);
        return chars[randomO] + substring;
    }

    /**
     * 时间戳转62进制
     *
     * @return
     */
    private static String encode() {
        long num = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        int remainder;
        while (num > radio - 1) {
            remainder = Long.valueOf(num % radio).intValue();
            sb.append(chars[remainder]);
            num = num / radio;
        }
        sb.append(chars[(Long.valueOf(num).intValue())]);
        String value = sb.reverse().toString();
        return StringUtils.leftPad(value, 7, '0');
    }
}
