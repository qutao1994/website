package com.rookie.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;

public class WebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(MvcConfig.class);
        /**
         * 使用servlert3.0的特性在启动的时候加入filter
         * 添加spring的CharacterEncodingFilter,解决乱码问题
         */
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setForceEncoding(true);
        filter.setEncoding("utf-8");
        FilterRegistration filterRegistration =  servletContext.addFilter("encodingFilter",filter);
        filterRegistration.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class),true,"/*");

        ctx.setServletContext(servletContext);
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        dispatcher.addMapping("/*");
        // /* 配置之后才能访问jsp文件  / 并不能访问/文件  这两个在过servlet的时候有什么区别,从源码的角度考虑！
        dispatcher.setLoadOnStartup(111);
    }


}
