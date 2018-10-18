package com.shnnny.notBlog.util;

/**
 * @author zhangzhh
 * @date 2018/10/18 21:53
 */
public class MessageUtils {

    public static String getMessage(String template, String... keys) {
        int count = 0;
        for (String key : keys) {
            template = template.replace("{" + count++ + "}", key);
        }
        return template;
    }
}
