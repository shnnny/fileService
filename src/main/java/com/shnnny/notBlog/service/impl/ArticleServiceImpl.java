package com.shnnny.notBlog.service.impl;

import com.shnnny.notBlog.comm.exception.TipException;
import com.shnnny.notBlog.model.po.Article;
import com.shnnny.notBlog.repository.ArticleRepository;
import com.shnnny.notBlog.service.ArticleService;
import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author zhangzhh
 * @date 2018/10/8 16:13
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    private ArticleRepository articleRepository;


    @Override
    public void publish(Article article) {
        articleRepository.saveAndFlush(article);


    }

    @Override
    public void updateArticle(Article article) {
        if (null == article || null == article.getArticleId()) {
            throw new TipException("文章对象不能为空");
        }
        if (StringUtils.isBlank(article.getTitle())) {
            throw new TipException("文章标题不能为空");
        }
        if (StringUtils.isBlank(article.getContents())) {
            throw new TipException("文章内容不能为空");
        }
        if (article.getTitle().length() > 200) {
            throw new TipException("文章标题过长");
        }
        if (article.getContents().length() > 65000) {
            throw new TipException("文章内容过长");
        }
        if (null == article.getAuthorId()) {
            throw new TipException("请登录后发布文章");
        }
        if (StringUtils.isBlank(article.getSlug())) {
            article.setSlug(null);
        }

        article.setModified(System.currentTimeMillis());
        //Integer articleId = article.getArticleId();
        article.setContents(EmojiParser.parseToAliases(article.getContents()));

        articleRepository.saveAndFlush(article);
        //relationshipService.deleteById(articleId, null);
        //metasService.saveMetas(cid, contents.getTags(), Types.TAG.getType());
        //metasService.saveMetas(cid, contents.getCategories(), Types.CATEGORY.getType());
    }

    @Override
    public void deleteByArticleId(Integer articleId) {

    }

    @Override
    public void updateArticleByArticleId(Article article) {

        if (null != article && null != article.getArticleId()) {
            articleRepository.saveAndFlush(article);
        }
    }

    /**
     * 列表
     * @param page 当前页
     * @param limit 每页条数
     * @return
     */
    @Override
    public Page<Article> getArticles(Integer page, Integer limit) {

        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page, limit, sort);

        Page<Article> all = articleRepository.findAll(pageable);

        return all;
    }

    @Override
    public Article getArticles(String id) {
        return null;
    }

    @Override
    public Page<Article> getArticles(Integer mid, Integer page, Integer limit) {
        return null;
    }

    @Override
    public void updateCategory(String ordinal, String newCategory) {

    }
}
