package com.shnnny.notBlog.comm;

import org.springframework.stereotype.Component;

/**
 * @author zhangzhh
 * @date 2018/10/17 20:03
 */
@Component
public class CommGlobal {

    /**
     * 登录的sessionkey
     */
    public static String LOGIN_SESSION_KEY = "Favorites_user";

    /**
     * 密码key
     */
    public static String PASSWORD_KEY = "@#$%^&*()OPG#$%^&*(HG";

    public static String DES3_KEY = "9964DYByKL967c3308imytCB";

    public static String LAST_REFERER = "LAST_REFERER";

    /**
     * cookie过期时间
     */
    public static int COOKIE_TIMEOUT= 30*24*60*60;

    /**
     * aes加密加盐
     */
    public static String AES_SALT = "0123456789abcdef";

}
