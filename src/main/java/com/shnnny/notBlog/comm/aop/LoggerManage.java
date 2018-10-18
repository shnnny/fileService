package com.shnnny.notBlog.comm.aop;

import java.lang.annotation.*;

/**
 * 日志注解
 * @author zhangzhh
 * @date 2018/10/17 19:31
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoggerManage {
    public String description();
}
