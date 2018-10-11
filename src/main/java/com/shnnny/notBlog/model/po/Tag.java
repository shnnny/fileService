package com.shnnny.notBlog.model.po;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author zhangzhh
 * @date 2018/10/11 18:20
 * 标签
 */
@Entity
@Table(name="blog_tag")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer tagId;

    /**
     * 标签名称
     */
    private String title;

    /**
     * 标签关联的已发布文章计数 -- 注意是已发布文章
     */
    private Integer publishedRefCount;

    /**
     * 标签关联的文章计数　　－－全部的文章
     */
    private Integer referenceCount;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    @Column(length =255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(length = 19)
    public Integer getPublishedRefCount() {
        return publishedRefCount;
    }

    public void setPublishedRefCount(Integer publishedRefCount) {
        this.publishedRefCount = publishedRefCount;
    }

    @Column(length = 19)
    public Integer getReferenceCount() {
        return referenceCount;
    }

    public void setReferenceCount(Integer referenceCount) {
        this.referenceCount = referenceCount;
    }
}
