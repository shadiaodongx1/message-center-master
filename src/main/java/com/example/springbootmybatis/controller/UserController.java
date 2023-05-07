package com.example.springbootmybatis.controller;

import com.example.springbootmybatis.mapper.UserMapper;
import com.example.springbootmybatis.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserMapper userMapper;


    @RequestMapping("/query")
    public String queryAll(Model model) {

//        public String list(Model model){
//            System.out.println("hello");
//            Collection<Employee> employees =  employDao.getAll();
//            model.addAttribute("emps",employees);
//            return "emp/list";
//        }

        List<user> users = userMapper.queryUserList();
        System.out.println(users);
        model.addAttribute("UserList",users);
        return "list1";
    }

//        List<user> users = userMapper.queryUserList();
//        for (user user : users) {
//            System.out.println(user);
//        }
//        return users;
//    }

    @RequestMapping("/query1")
    @ResponseBody
    public String query1(Model model){
        user user = userMapper.queryUserById(1);
        return user.getUsername();
    }
    }
