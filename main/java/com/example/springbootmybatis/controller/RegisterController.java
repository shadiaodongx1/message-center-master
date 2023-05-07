package com.example.springbootmybatis.controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springbootmybatis.mapper.AdminMapper;
import com.example.springbootmybatis.mapper.UserMapper;
import com.example.springbootmybatis.pojo.admin;
import com.example.springbootmybatis.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;

import org.springframework.web.bind.annotation.RequestParam;
import com.example.springbootmybatis.mapper.MailServer;

import java.nio.charset.StandardCharsets;

@Controller
public class RegisterController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AdminMapper adminMapper;

    @RequestMapping("/toregister")
    public String register(user user, @RequestParam("password") String password,
                           @RequestParam("password1") String password1, Model model){
        System.out.println(user + " is register");

   if(password.length()>=8
                && PwdCheckUtil.checkContainCase(user.getPassword())
                && PwdCheckUtil.checkContainDigit(user.getPassword())
                && PwdCheckUtil.checkContainSpecialChar(user.getPassword())
                )
       {

       if (userMapper.queryUserByName(user.getUsername())!=null)
       {
               model.addAttribute("registermsg","用户名已被使用，请更换");
               return "register";
       }
       else{
               if (password.equals(password1)){
               String md5temp = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
               user.setPassword(md5temp);
               int i = userMapper.addUser(user);
               System.out.println(i);
               model.addAttribute("registermsg","你已经注册成功，请登录");
               return "register";
       }
       else {
           model.addAttribute("registermsg","两次注册密码不一致，请重新注册");
           return "register";
       }}


       }
   else
       {
           model.addAttribute("registermsg","密码需要有特殊字符，字母，数字，且长度大于8位");
       }
       return "register";
    }

    @RequestMapping("/toregisteradmin")
    public String register2(user user, admin admin, @RequestParam("adminpwd") String password,
                            @RequestParam("adminpwd1") String password1, Model model){
        System.out.println(user + " is register");
        if (password.equals(password1)){
            System.out.println(admin);
            int a = adminMapper.addAdmin(admin);
            //int i = userMapper.addUser(user);
            int y = userMapper.addUser1(admin.getAdminname(),admin.getAdminpwd());
           // System.out.println(i);
            model.addAttribute("registermsg","新管理员已经注册成功");
            return "dashboard";
        }
        else {
            model.addAttribute("registermsg","两次注册密码不一致，请重新注册");
            return "operate/adminregister";
        }


    }
}

