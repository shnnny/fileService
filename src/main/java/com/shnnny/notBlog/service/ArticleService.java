package com.shnnny.notBlog.service;

import com.shnnny.notBlog.model.po.Article;
import org.springframework.data.domain.Page;

public interface ArticleService {


    /**
     * 发布文章
     * @param article
     */
    void publish(Article article);


    /**
     * 根据文章id删除
     * @param articleId
     */
    void deleteByArticleId(Integer articleId);


    /**
     * 编辑文章
     * @param article
     */
    void updateArticle(Article article);
    /**
     * 根据主键更新 例如更新状态，分类，等等
     * @param article
     */
    void updateArticleByArticleId(Article article);

    /**
     * 查询文章返回多条数据
     * @param page 当前页
     * @param limit 每页条数
     * @return Article
     */
    Page<Article> getArticles(Integer page, Integer limit);

    /**
     * 根据id获取文章的别名slug文章
     * @param id id
     * @return Article
     */
    Article getArticles(String id);

    /**
     * 查询分类/标签下的文章归档
     * @param mid 分类标签的id
     * @param page 当前页数
     * @param limit 每页的条数
     * @return Article
     */
    Page<Article> getArticles(Integer mid,Integer page, Integer limit);

    /**
     * 更新原有文章的category
     * @param ordinal 原有的category
     * @param newCategory 新的category
     */
    void updateCategory(String ordinal,String newCategory);


}
