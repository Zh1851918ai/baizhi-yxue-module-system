package com.baizhi.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 作者： zouxf
 * 类的作用:
 * 类的创建时间： 2020/9/1 14:30
 */
public class MyIntercetor1 implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        /*System.out.println("===invoke MyIntercetor1 preHandle===");*/
        /**
         * 返回值代表是否让请求继续向下执行
         */
        return true;
    }
}
