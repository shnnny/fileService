package com.shnnny.notBlog.bean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 系统中重要操作日志
 */
@Entity
@Table(name = "blog_log")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 日志主键
     */
    private Integer logId;

    /**
     * 产生的动作
     */
    private String action;

    /**
     * 产生的数据
     */
    private String data;

    /**
     * 发生人id
     */
    private Integer authorId;

    /**
     * 日志产生的ip
     */
    private String ip;

    /**
     * 日志创建时间
     */
    private Integer created;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    @Column(length = 200)
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Column(length = 200)
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Column(length = 40)
    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    @Column(length = 20)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Column(length = 40)
    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }
}
