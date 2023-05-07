package com.example.springbootmybatis.controller;

import com.example.springbootmybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class deleteController {

        @Autowired
        private UserMapper mapper;
        @RequestMapping("/delete1")
        //删除留言和评论
        public String deletemescom(@RequestParam("id") Integer id){
            int i = mapper.deleteComMes(id);
            System.out.println(i);
            return "dashboard";
        }

    @RequestMapping("/delete2")
    //删除用户
    public String deleteuser(@RequestParam("id") Integer id){

        int i = mapper.deleteUser(id);
        System.out.println(i);
        return "dashboard";
    }
}
