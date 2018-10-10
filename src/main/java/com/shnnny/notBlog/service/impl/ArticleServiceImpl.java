package com.shnnny.notBlog.service.impl;

import com.shnnny.notBlog.model.po.Article;
import com.shnnny.notBlog.repository.ArticleContentRepository;
import com.shnnny.notBlog.repository.ArticleRepository;
import com.shnnny.notBlog.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangzhh
 * @date 2018/10/8 16:13
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleContentRepository articleContentRepository;


    public void publish(Article article) {
        articleRepository.saveAndFlush(article);
        articleContentRepository.saveAndFlush(article.getArticleContent());

    }

    public void deleteByArticleId(Integer articleId) {

    }

    public void updateArticleByArticleId(Article article) {

    }

    /**
     * 列表
     * @param page 当前页
     * @param limit 每页条数
     * @return
     */
    public Page<Article> getArticles(Integer page, Integer limit) {

        Pageable pageable = null;
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        PageRequest of = PageRequest.of(page, limit, sort);

        PageRequest of1 = PageRequest.of(1, 1);
        Pageable pageable1 = of1.previousOrFirst();
        pageable = of.next();

        Page<Article> all = articleRepository.findAll(pageable);

        return all;
    }

    public Article getArticles(String id) {
        return null;
    }

    public Page<Article> getArticles(Integer mid, Integer page, Integer limit) {
        return null;
    }

    public void updateCategory(String ordinal, String newCategory) {

    }
}
