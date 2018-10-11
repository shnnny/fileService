package com.shnnny.notBlog.controller;

import com.shnnny.notBlog.model.po.User;
import com.shnnny.notBlog.util.JsonUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhangzhh
 * @date 2018/10/10 20:35
 */
public abstract class AbstractWebController extends AbstractController{

    /**
     * 存放当前线程的HttpServletResponse对象
     */
    private static ThreadLocal<HttpServletResponse> httpServletResponseThreadLocal = new ThreadLocal();

    protected static final String TOKEN_ATTRIBUTE_NAME = "token";

    protected static final String USERNAME_ATTRIBUTE_NAME = "username";

    protected static final String NICKNAME_ATTRIBUTE_NAME = "nickname";




    /**
     * 成功登录后处理session
     *
     * @param token
     */
    protected void loginSuccess(String token) {
        setSessionAttribute(TOKEN_ATTRIBUTE_NAME, token);
    }

    protected void afterLoginSuccess(User loginWebUser) {
        setSessionAttribute(USERNAME_ATTRIBUTE_NAME, loginWebUser.getUsername());
        setSessionAttribute(NICKNAME_ATTRIBUTE_NAME, loginWebUser.getNickname());
    }

    /**
     * 成功注销后处理session
     */
    protected void logoutSuccess() {
        setSessionAttribute(USERNAME_ATTRIBUTE_NAME, null);
        setSessionAttribute(NICKNAME_ATTRIBUTE_NAME, null);
        setSessionAttribute(TOKEN_ATTRIBUTE_NAME, null);
    }

    /**
     * 获取用户的username
     *
     * @return
     */
    protected String getUsername() {
        return (String) getSessionAttribute(USERNAME_ATTRIBUTE_NAME);
    }

    /**
     * 获取用户的token
     *
     * @return
     */
    protected String getToken() {
        return (String) getSessionAttribute(TOKEN_ATTRIBUTE_NAME);
    }

    /**
     * 获取当前线程的HttpServletResponse对象
     *
     * @return 当前线程的HttpServletResponse对象
     */
    protected HttpServletResponse getResponse() {
        return httpServletResponseThreadLocal.get();
    }


    /**
     * 客户端返回JSON字符串
     *
     * @param object
     * @return
     */
    protected void renderJson(Object object) {
        renderText(JsonUtils.bean2Json(object), "application/json");
    }
    /**
     * 客户端返回字符串
     *
     * @param string
     * @return
     */
    protected void renderText(String string, String type) {
        try {
            HttpServletResponse response = getResponse();
            response.reset();
            response.setContentType(type);
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
