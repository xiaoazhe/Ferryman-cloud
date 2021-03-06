package com.ferry.consumer.config;

import com.ferry.consumer.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    private JwtInterceptor jwtInterceptor;
    protected void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器要声明拦截器对象和要拦截的请求
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/problem/**")
                .excludePathPatterns("/blog/**")
                .excludePathPatterns("/type/**")
                .excludePathPatterns("/user/**")
                .excludePathPatterns("/label/**")
                .excludePathPatterns("/reply/**")
                .excludePathPatterns("/gossip/**")
                .excludePathPatterns("/link/**")
                .excludePathPatterns("/material/**")
                .addPathPatterns("/blog/saveBlog")
                .addPathPatterns("/reply/save")
                .addPathPatterns("/gossip/save")
                .addPathPatterns("/problem/save")
                .addPathPatterns("/link/save")
                .addPathPatterns("/problem/collect/**");
    }
}
