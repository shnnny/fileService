package com.shnnny.notBlog.comm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zhangzhh
 * @date 2018/10/17 20:03
 */
@Component
public class CommGlobal {
    public static String BASE_PATH;

    public static String LOGIN_SESSION_KEY = "Favorites_user";

    public static String PASSWORD_KEY = "@#$%^&*()OPG#$%^&*(HG";

    public static String DES3_KEY = "9964DYByKL967c3308imytCB";

    public static String LAST_REFERER = "LAST_REFERER";

    public static int COOKIE_TIMEOUT= 30*24*60*60;

    /**
     * aes加密加盐
     */
    public static String AES_SALT = "0123456789abcdef";

    @Autowired(required = true)
    public void setBasePath(@Value("${favorites.base.path}")String basePath) {
        CommGlobal.BASE_PATH = basePath;
    }
}
