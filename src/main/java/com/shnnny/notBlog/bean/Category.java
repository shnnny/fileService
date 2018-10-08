package com.shnnny.notBlog.bean;

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
     * 创建时间
     */
    private Integer created;


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

    @Column(length = 20)
    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }
}
