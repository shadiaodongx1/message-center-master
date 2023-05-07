package com.example.springbootmybatis.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//设置登陆拦截器 只要继承HandlerInterceptor 则为拦截器
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登陆之后 应该有用户的session
        Object loginUserName = request.getSession().getAttribute("userwho");
        Object loginUserPassword = request.getSession().getAttribute("userwho");
        if (loginUserName!=null&&loginUserPassword!=null){
            return true;
        }else {
            request.setAttribute("msg","没有权限,请先登陆");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }

    }
}
