package com.comtop.mobile.market.util

/**
 * Created by zhaoqunqi on 2014/11/19.
 */
class JsonHelper {

    public static String onSuccessMessage(String msg) {
        return "{\"code\":1,\"msg\":${msg}}"
    }

    public static String onSuccessBody(String body) {
        return "{\"code\":1,\"body\":${body}}"
    }

    public static String onSuccess(String msg, String body) {
        return "{\"code\":1,\"msg\":${msg},\"body\":${body}}"
    }

    public static String onError(String errorMessage) {
        return "{\"code\":-1,\"msg\":${errorMessage}}"
    }
}
