package com.shnnny.notBlog.controller.admin;

import com.shnnny.notBlog.controller.AbstractWebController;
import com.shnnny.notBlog.exception.TipException;
import com.shnnny.notBlog.model.dto.Result;
import com.shnnny.notBlog.model.po.Article;
import com.shnnny.notBlog.model.po.ArticleContent;
import com.shnnny.notBlog.service.ArticleContentService;
import com.shnnny.notBlog.service.ArticleService;
import com.shnnny.notBlog.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author zhangzhh
 * @date 2018/10/10 20:27
 */
@RestController
@RequestMapping("/admin/article")
@Transactional(rollbackFor = TipException.class)
public class ArticleController extends AbstractWebController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleContentService articleContentService;


    /**
     * 文章发表
     * @param request
     * @return
     */
    @PostMapping(value = "/publish")
    @Transactional(rollbackFor = TipException.class)
    public Result publishArticle(HttpServletRequest request, Article article , ArticleContent articleContent) {

        HttpSession session = getSession();
        article.setArticleContent(articleContent);

        articleService.publish(article);



        return ResultUtils.success();
    }

}
