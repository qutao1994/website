package com.rookie.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

        ctx.register(MvcConfig.class);
        ctx.setServletContext(servletContext);
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        dispatcher.addMapping("/*");
        // /* 配置之后才能访问jsp文件  / 并不能访问/文件  这两个在过servlet的时候有什么区别,从源码的角度考虑！
        dispatcher.setLoadOnStartup(111);
    }
}
