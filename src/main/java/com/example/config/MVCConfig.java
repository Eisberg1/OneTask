package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


/**
 * Author:Sphinx
 * Date:2019/03/25 16:30
 * Description:
 */
@Configuration
public class MVCConfig implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/main").setViewName("jsp/main");
        registry.addViewController("/report").setViewName("jsp/report");
        registry.addViewController("/upload").setViewName("html/report");
    }

    @Bean
    InternalResourceViewResolver jspViewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewNames("jsp/*");
        viewResolver.setOrder(2);
        return viewResolver;
    }




}
