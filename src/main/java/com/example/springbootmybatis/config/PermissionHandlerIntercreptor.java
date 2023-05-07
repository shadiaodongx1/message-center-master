//package com.example.spingbootmybatis.config;
//
//import com.example.spingbootmybatis.mapper.AdminMapper;
//import com.example.spingbootmybatis.pojo.admin;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Service;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class PermissionHandlerIntercreptor implements HandlerInterceptor {
//
//    @Autowired
//    private AdminMapper adminMapper;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //登陆之后 通过扫描管理员数据库判断是否拥有权限
//        String userwho = (String) request.getSession().getAttribute("userwho");
//        String userpwd = (String) request.getSession().getAttribute("userpwd");
//        System.out.println(userwho);
//        System.out.println(userpwd);
//        admin Admin = adminMapper.queryAdminByNP(userwho,userpwd);
//        System.out.println("admin"+Admin);
////        session.setAttribute("userwho",user.getUsername());
////        session.setAttribute("userpwd",user.getPassword());
//
//        if (Admin!=null){
//            return true;
//        }else {
//            request.setAttribute("permission","没有权限,您不是管理员");
//            request.getRequestDispatcher("/main.html").forward(request,response);
//            return false;
//        }
//    }
//}
