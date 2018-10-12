package com.shnnny.notBlog.model.po;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author zhangzhh
 * @date 2018/10/11 18:40
 * 友链表
 */
@Entity
@Table(name="blog_link")
public class Link implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Integer linkId;

    /**
     * 链接地址
     */
    private String address;

    /**
     * 链接描述
     */
    private String description;

    /**
     * 链接标题
     */
    private String title;

    /**
     * 连接展现的排序
     */
    private Integer sort;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    @Column(length =255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(length =255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(length =126)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(length =20)

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
