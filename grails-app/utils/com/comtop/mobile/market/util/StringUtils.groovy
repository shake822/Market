package com.comtop.mobile.market.util

/**
 * Created by zhaoqunqi on 14-11-30.
 */
class StringUtils {
    public static boolean isBlank(String string) {
        if (string == null || string.length() == 0) {
            return true
        }
        return false
    }
}
