package com.shnnny.notBlog;

import com.shnnny.notBlog.intercept.CrossUrlInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author zhangzhh
 * @date 2018/10/10 14:46
 * 相当于web.xml
 * WebMvcConfigurationSupport 继承
 * WebMvcConfigurer 实现接口
 * 上面有两个有区别 当不需要返回逻辑视图的时候采用第一个 如果需要返回逻辑视图采用第二个重写addInterceptor方法
 */
@Configuration
public class WebConfigurer extends WebMvcConfigurationSupport {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CrossUrlInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}
