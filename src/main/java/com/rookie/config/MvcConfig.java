package com.rookie.config;

import com.rookie.interceptor.RepeatClickInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.servlet.FilterRegistration;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        resolver.setMaxUploadSize(540000);
        try {
            resolver.setUploadTempDir(new PathResource("D://upload"));
        } catch (IOException e) {
        }
        return resolver;
    }

    @Bean
    public  CharacterEncodingFilter characterEncodingFilter(){
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("utf-8");
        filter.setForceEncoding(true);
        return filter;
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
        CharacterEncodingFilter encodingFilter =  new CharacterEncodingFilter();
    }

    /**
     * 增加简单controller 如果说没有逻辑直接返回视图时,就可以这么写
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
       // registry.addViewController("/upload").setViewName("upload");

    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter converter = new StringHttpMessageConverter();
        /**
         *   MediaType 的类可以这样使用,查看parseMediaType 源码解析弄出来的
         *  HashMap<String,String> map = new HashMap<>();
             map.put("chartset", "utf-8");
             MediaType charset1 = new MediaType("text","plain", map);
         */
        List<MediaType> lists = new ArrayList<>();
        lists.add(MediaType.parseMediaType("text/plain;charset=UTF-8"));
        lists.add(MediaType.parseMediaType("text/html;charset=UTF-8"));
        converter.setSupportedMediaTypes(lists);
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        converters.add(converter);
        converters.add(jsonConverter);
    }
    /**
     * 参数带.时，.后边的部分会被过滤掉，设置不过滤掉
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }
}

