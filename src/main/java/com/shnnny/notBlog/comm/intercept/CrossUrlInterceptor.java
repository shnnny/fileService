package com.shnnny.notBlog.comm.intercept;

import com.shnnny.notBlog.cache.MapCache;
import com.shnnny.notBlog.comm.CommGlobal;
import com.shnnny.notBlog.model.Types;
import com.shnnny.notBlog.model.po.User;
import com.shnnny.notBlog.service.UserService;
import com.shnnny.notBlog.util.BlogUtils;
import com.shnnny.notBlog.util.IPKitUtils;
import com.shnnny.notBlog.util.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhangzhh
 * @date 2018/10/10 14:43
 * 统一拦截response进行设置
 */
public class CrossUrlInterceptor implements HandlerInterceptor {
    private static final Logger LOGGE = LoggerFactory.getLogger(CrossUrlInterceptor.class);
    private static final String USER_AGENT = "user-agent";

    private MapCache cache = MapCache.single();

    @Resource
    private UserService userService;



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       /* BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        UserService userService1 = (UserService)factory.getBean("userServiceImpl");*/
        String requestURI = request.getRequestURI();

        LOGGE.info("UserAgent: {}", request.getHeader(USER_AGENT));
        LOGGE.info("用户访问地址: {}, 来路地址: {}", requestURI, IPKitUtils.getIpAddrByRequest(request));
/*

        //请求拦截处理
        User user = BlogUtils.getLoginUser(request);

        if (null == user) {
            Integer uid = BlogUtils.getCookieUid(request);
            if (null != uid) {
                //这里还是有安全隐患,cookie是可以伪造的
                user = userService.queryUserById(uid);
                request.getSession().setAttribute(CommGlobal.LOGIN_SESSION_KEY, user);
            }
        }
        String method = request.getMethod();
        //设置get请求的token
        if ("GET".equals(request.getMethod())) {
            String csrf_token = UUIDUtils.UU64();
            // 默认存储30分钟
            cache.hset(Types.CSRF_TOKEN.getType(), csrf_token, requestURI, 30 * 60);
            request.setAttribute("_csrf_token", csrf_token);
        }
*/





        //指定允许其他的域名访问
        response.setHeader("Access-Control-Allow-Origin","*");
        //响应的类型
        response.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,DELETE");
        response.setHeader("Access-Control-Max-Age","3600");
        //响应头设置
        response.setHeader("Access-Control-Allow-Headers","Origin,X-Requested-With,Content-Type,Accept");
        //只有返回true才后继续往下执行，返回false取消当前请求
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
