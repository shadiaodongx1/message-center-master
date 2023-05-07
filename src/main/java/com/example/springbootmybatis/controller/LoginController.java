package com.example.springbootmybatis.controller;

import com.example.springbootmybatis.mapper.UserMapper;
import com.example.springbootmybatis.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;

@Controller
public class LoginController {
    @Autowired
    private UserMapper userMapper;



    @RequestMapping("/login")
    public String Login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpServletRequest httpServletRequest,
                        Model model, HttpSession session){
        //具体的业务
        System.out.println(username);
        System.out.println(password);
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));

        user user = userMapper.queryUserByNP(username, password);
        System.out.println(user);
        boolean captchaflag=false;
        String captchaId = (String) httpServletRequest.getSession().getAttribute("vrifyCode");
        String parameter = httpServletRequest.getParameter("vrifyCode");
        System.out.println("Session vrifyCode "+captchaId+" form vrifyCode "+parameter);

        if (!captchaId.equalsIgnoreCase(parameter)) {
            captchaflag=false;
        } else {
            captchaflag=true;
        }

        if (user!=null&& captchaflag)
        {
            session.setAttribute("userwho",user.getUsername());
            session.setAttribute("userpwd",user.getPassword());
            //return "list1";

            return "redirect:/main.html";   //登录成功后重定向到第一页
        }
        else if(!captchaflag){
            model.addAttribute("loginerror","验证码错误");
            return "index";
        }
        else{
            model.addAttribute("loginerror","用户名或者密码错误");
            return "index";
        }
    }


    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        System.out.println("logout");
        return "redirect:/index.html";
    }

}
