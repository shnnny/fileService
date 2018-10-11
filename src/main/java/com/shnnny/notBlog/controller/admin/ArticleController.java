package com.shnnny.notBlog.controller.admin;

import com.shnnny.notBlog.controller.AbstractWebController;
import com.shnnny.notBlog.exception.TipException;
import com.shnnny.notBlog.model.dto.Result;
import com.shnnny.notBlog.model.po.Article;
import com.shnnny.notBlog.service.ArticleService;
import com.shnnny.notBlog.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
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


    /**
     * 文章发表
     * @param request
     * @return
     */
    @PostMapping(value = "/publish")
    @Transactional(rollbackFor = TipException.class)
    public Result publishArticle(HttpServletRequest request, Article article) {

        HttpSession session = getSession();


        articleService.publish(article);



        return ResultUtils.success();
    }

    @GetMapping("{id}")
    public Result getArticle(@PathVariable("id")String id){
        HttpServletRequest request = getRequest();
        HttpSession session = getSession();
        Model model = getModel();
        ServletContext context = getContext();
        String remoteIp = getRemoteIp();
        String token = getToken();

        return ResultUtils.success();
    }
}
