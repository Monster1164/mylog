package com.cb.myblog.intercept;

import org.omg.PortableInterceptor.Interceptor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginIntercept implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(request.getSession().getAttribute("user")==null){
            response.sendRedirect("/admin");
            return false;
        }

        return true;
    }
}
