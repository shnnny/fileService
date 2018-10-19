package com.shnnny.notBlog.controller;

import com.shnnny.notBlog.comm.CommGlobal;
import com.shnnny.notBlog.model.po.User;
import com.shnnny.notBlog.util.JsonUtils;
import com.shnnny.notBlog.util.MD5Utils;
import com.shnnny.notBlog.util.Tools;

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
     * 进行密码加密，抽取的公共方法
     * @param password
     * @return
     */
    protected String getPwd(String password){
        try {
            String pwd = MD5Utils.encrypt(password+ CommGlobal.PASSWORD_KEY);
            return pwd;
        } catch (Exception e) {
            LOGGER.error("密码加密异常：",e);
        }
        return null;
    }

    /**
     * 通过aes加密生成cookie的签名
     * @param value
     * @return
     */
    protected String cookieSign(String value){
        try{
            value = value + CommGlobal.PASSWORD_KEY;
            String sign = Tools.enAes(value, CommGlobal.AES_SALT);
            return sign;
        }catch (Exception e){
            LOGGER.error("cookie签名异常：",e);
        }
        return null;
    }

    /**
     * 获取当前session中登录的人
     * @return
     */
    protected User getUser() {
        return (User) getSession().getAttribute(CommGlobal.LOGIN_SESSION_KEY);
    }


    /**
     * 成功登录后处理session
     *
     * @param token
     */
    protected void loginSuccess(String token) {
        setSessionAttribute(TOKEN_ATTRIBUTE_NAME, token);
    }

    protected void afterLoginSuccess(User loginWebUser) {
        setSessionAttribute(USERNAME_ATTRIBUTE_NAME, loginWebUser.getUserName());
        setSessionAttribute(NICKNAME_ATTRIBUTE_NAME, loginWebUser.getNickName());
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
