package com.shnnny.notBlog.controller.admin;

import com.shnnny.notBlog.comm.exception.TipException;
import com.shnnny.notBlog.controller.AbstractWebController;
import com.shnnny.notBlog.model.LogActions;
import com.shnnny.notBlog.model.Types;
import com.shnnny.notBlog.model.po.Article;
import com.shnnny.notBlog.model.result.Result;
import com.shnnny.notBlog.model.result.ResultUtils;
import com.shnnny.notBlog.service.ArticleService;
import com.shnnny.notBlog.service.LogService;
import org.apache.commons.lang3.StringUtils;
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

    private static final String DEFAULT_CATEGORY = "默认分类";

    @Autowired
    private ArticleService articleService;
    @Autowired
    private LogService logService;


    /**
     * 文章发表
     * @param request
     * @return
     */
    @PostMapping(value = "/publish")
    @Transactional(rollbackFor = TipException.class)
    public Result publishArticle(@RequestBody Article article,HttpServletRequest request) {

       //TODO:存储到数据库中
        article.setCreated(System.currentTimeMillis());
        //article.setAuthorId(getUser().getUserId());
        article.setStatus(Types.PUBLISH.getType());
        if (StringUtils.isBlank(article.getBlogCategory())) {
            article.setBlogCategory(DEFAULT_CATEGORY);
        }
        try {

            articleService.publish(article);
        } catch (Exception e) {
            String msg = "文章发布失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg, e);
            }
            return ResultUtils.error(msg);
        }

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

    /**
     * 文章更新
     * @param article
     * @param request
     * @return
     */
    @PostMapping(value = "/modify")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public Result modifyArticle(@RequestBody Article article,HttpServletRequest request) {

        article.setAuthorId(getUser().getUserId());
        article.setType(Types.PUBLISH.getType());
        try {
            articleService.updateArticle(article);
        } catch (Exception e) {
            String msg = "文章编辑失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg, e);
            }
            return ResultUtils.error(msg);
        }
        return ResultUtils.success();
    }

    /**
     * 删除文章
     * @param articleId
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public Result delete(@RequestParam int articleId, HttpServletRequest request) {
        try {
            // contentsService.deleteByCid(cid);
            Article article = articleService.getArticles(articleId+"");
            //XXX:这里用状态代表是否删除
            article.setStatus(Types.REVOKE.getType());
            articleService.updateArticleByArticleId(article);
            logService.insertLog(LogActions.DEL_RUBBISH_ARTICLE.getAction(), articleId+"", getRemoteIp(), getUser().getUserId());
        } catch (Exception e) {
            String msg = "文章删除失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg, e);
            }
            return ResultUtils.error(msg);
        }
        return ResultUtils.success();
    }
}
