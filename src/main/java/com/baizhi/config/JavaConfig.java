package com.baizhi.config;

import com.baizhi.interceptor.MyIntercetor1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 作者： zouxf
 * 类的作用: 这是一个配置类
 * 类的创建时间： 2020/9/1 14:32
 */
@Configuration
public class JavaConfig {

    // 声明拦截器交给spring工厂
    @Bean
    public MyIntercetor1 myIntercetor1() {
        return new MyIntercetor1();
    }

    // 注册一个拦截器: 我们需要自己实现WebMvcConfigurer接口
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new InterceptorRegister();
    }

    class InterceptorRegister implements WebMvcConfigurer {

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            // 进行拦截器注册
            InterceptorRegistration registration = registry.addInterceptor(myIntercetor1());

            // 配置拦截范围
            registration.addPathPatterns("/**");// 代表拦截所有
            // 排除指定控制器不拦截 : 在上面所有范围的基础上进行的排除
            registration.excludePathPatterns("/hello/jerry");
        }
    }
}
