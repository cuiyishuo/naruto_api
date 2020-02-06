package com.solplatform.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器实现类
 *
 * @author sol
 * @create 2020-02-06  21:59
 */
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.err.println ("进入拦截器");
        String authorization = httpServletRequest.getHeader ("authorization");
        System.err.println ("authorization:" + authorization);
        // 暂时先判空，后续换成jwt
        if (null == authorization) {
            System.err.println ("authorization:" + authorization);
            // 抛出异常，在统一的异常捕获类中处理
            throw new RuntimeException ("未登录或token已失效，请重新登录");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
