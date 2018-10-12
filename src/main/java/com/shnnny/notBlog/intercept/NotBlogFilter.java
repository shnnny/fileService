package com.shnnny.notBlog.intercept;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author zhangzhh
 * @date 2018/10/12 12:14
 */
@WebFilter(filterName="NotBlogFilter",urlPatterns = { "*"})
public class NotBlogFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        long l = System.currentTimeMillis();

    }

    public void destroy() {

    }
}
