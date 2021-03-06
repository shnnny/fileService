package com.shnnny.notBlog.model.po;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 文章
 */
@Entity
@Table(name="blog_article")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 表主键-文章id
     */
    private Integer articleId;

    /**
     * 内容标题
     */
    private String title;

    /**
     * 内容缩略名
     */
    private String slug;

    /**
     * 内容生成时的GMT unix时间戳
     */
    private Long created;

    /**
     * 内容更改时的GMT unix时间戳
     */
    private Long modified;

    /**
     * 内容所属用户id
     */
    private Integer authorId;

    /**
     * 文章类型-原创转载翻译
     */
    private String type;

    /**
     * 文章状态 -- 草稿 -- 发布 -- 回收 -- 删除
     */
    private String status;

    /**
     * 标签列表 自定义五个
     */
    private String tags;

    /**
     * 分类列表  个人分类
     */
    private String personCategories;

    /**
     * 博客分类
     */
    private String blogCategory;
    /**
     * 点击次数
     */
    private Integer hits;

    /**
     * 内容所属评论数
     */
    private Integer commentsNum;

    /**
     * 是否允许评论
     */
    private Boolean allowComment;

    /**
     * 是否允许ping
     */
    private Boolean allowPing;

    /**
     * 内容
     */
    private String contents;



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    @Column(length = 40)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(length = 40)
    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Column(length = 40)
    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    @Column(length = 40)
    public Long getModified() {
        return modified;
    }

    public void setModified(Long modified) {
        this.modified = modified;
    }

    @Column(length = 40)
    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    @Column(length = 40)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(length = 40)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(length = 40)
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Column(length = 40)
    public String getPersonCategories() {
        return personCategories;
    }

    public void setPersonCategories(String personCategories) {
        this.personCategories = personCategories;
    }

    @Column(length = 40)
    public String getBlogCategory() {
        return blogCategory;
    }

    public void setBlogCategory(String blogCategory) {
        this.blogCategory = blogCategory;
    }

    @Column(length = 40)
    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    @Column(length = 40)
    public Integer getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(Integer commentsNum) {
        this.commentsNum = commentsNum;
    }

    @Column(length = 40)
    public Boolean getAllowComment() {
        return allowComment;
    }

    public void setAllowComment(Boolean allowComment) {
        this.allowComment = allowComment;
    }

    @Column(length = 40)
    public Boolean getAllowPing() {
        return allowPing;
    }

    public void setAllowPing(Boolean allowPing) {
        this.allowPing = allowPing;
    }


    @Lob
    @Column(columnDefinition="text")
    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

}