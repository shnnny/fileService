package com.shnnny.notBlog.model.po;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 分类
 */
@Entity
@Table(name="blog_category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类id
     */
    private Integer categoryId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类访问路径
     */
    private String url;

    /**
     * 分类描述
     */
    private String description;


    /**
     * 分类展现的排序
     */
    private Integer order;


    /**
     * 分类下聚合的标签计数
     */
    private Integer tagCnt;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Column(length = 20)
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Column(length = 200)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(length = 1000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(length = 20)
    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        order = order;
    }

    @Column(length = 20)
    public Integer getTagCnt() {
        return tagCnt;
    }

    public void setTagCnt(Integer tagCnt) {
        tagCnt = tagCnt;
    }
}
