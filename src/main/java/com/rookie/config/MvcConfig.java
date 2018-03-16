package com.rookie.config;

import com.rookie.interceptor.RepeatClickInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan("com.rookie.controller")
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Bean

    public InternalResourceViewResolver viewResolver(){

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/views/");
        //viewResolver.setSuffix(".html");
        viewResolver.setSuffix(".html");
        viewResolver.setViewClass(JstlView.class);

        return  viewResolver;
    }

    /**
     * 注册一个Handler  过滤静态资源
     * @param registry
     */

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/views/**").addResourceLocations("classpath:/views/");
    }

    /**
     * 添加一个拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RepeatClickInterceptor());
    }

    /**
     * 增加简单controller 如果说没有逻辑直接返回视图时,就可以这么写
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
    }
}

