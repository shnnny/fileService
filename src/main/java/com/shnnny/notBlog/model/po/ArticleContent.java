package com.shnnny.notBlog.model.po;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 内容
 */
@Entity
@Table(name = "blog_article_content")
public class ArticleContent implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *内容id
     */
    private Integer articleContentId;

    /**
     * 内容
     */
    private String contents;

    /**
     * 文章id
     */
    private String articleId;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getArticleContentId() {
        return articleContentId;
    }

    public void setArticleContentId(Integer articleContentId) {
        this.articleContentId = articleContentId;
    }

    @Lob
    @Column(columnDefinition="text")
    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Column(length = 40)
    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
}
