package com.example.springbootmybatis.config;
//扩展（自定义） springMVC的  dispatchservlet

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  //表明是一个配置类
public class MyMvcConfig implements WebMvcConfigurer {
    @Override  //复写方法
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/main.html").setViewName("dashboard");
        registry.addViewController("/register.html").setViewName("register");
        registry.addViewController("/404").setViewName("404");
        registry.addViewController("/zhuye").setViewName("dashboard");
        registry.addViewController("/forgetpwd.html").setViewName("forgetpwd");
        registry.addViewController("/resetpwd.html").setViewName("resetpwd");
        registry.addViewController("/chat.html").setViewName("chat");
//        registry.addViewController("/index.html").setViewName("index");
//        registry.addViewController("/main.html").setViewName("dashboard");
        //registry.addViewController("")
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new com.example.springbootmybatis.config.LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns(
                "/index.html",
                "/sendemail.html",
                "/register.html",
                "/",
                "/login",
                "/css/*",
                "/img/**",
                "/js/**",
                "/toregister",
                "/defaultKaptcha",
                "/imgvrifyControllerDefaultKaptcha",
                "/forgetpwd.html",
                "/resetpwd.html",
                "/toresetpwd",
                "/toforgetpwd",
                "/chat.html"
        );

//        registry.addInterceptor(new com.example.spingbootmybatis.config.PermissionHandlerIntercreptor()).addPathPatterns("/jumptouserlist");
//    }
    }
}
