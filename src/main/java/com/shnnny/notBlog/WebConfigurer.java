package com.shnnny.notBlog;

import com.shnnny.notBlog.intercept.CrossUrlInterceptor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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
@EnableAutoConfiguration
public class WebConfigurer extends WebMvcConfigurationSupport {

    /**
     * 解决在自定义拦截器中注入service为null的问题
     * @return
     */
    @Bean
    public CrossUrlInterceptor getCrossUrlInterceptor(){
        return  new CrossUrlInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getCrossUrlInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index.html","/","/user/login","/static/**");
        super.addInterceptors(registry);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/");
        super.addResourceHandlers(registry);
    }
   /* @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }*/

}
