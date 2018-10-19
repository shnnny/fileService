package com.shnnny.notBlog.model;

/**
 * @program: notBlog
 * @description: 日志动作枚举类
 * @author: edm.zhangzhh
 * @create: 2018-10-19 18:12
 **/
public enum LogActions {
    LOGIN("登录后台"),
    UP_PWD("修改密码"),
    UP_INFO("修改个人信息"),
    DEL_ARTICLE("删除文章"),
    DEL_RUBBISH_ARTICLE("把文章删除到回收站"),
    REVOKE_ARTICLE("撤销删除文章"),
    DEL_PAGE("删除页面"),
    SYS_BACKUP("系统备份"),
    SYS_SETTING("保存系统设置"),
    INIT_SITE("初始化站点");

    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    LogActions(String action) {
        this.action = action;
    }
}
