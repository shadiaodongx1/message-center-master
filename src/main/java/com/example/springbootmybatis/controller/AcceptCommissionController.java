//package com.example.springbootmybatis.controller;
//
//import com.example.springbootmybatis.mapper.EntrustmentMapper;
//import com.example.springbootmybatis.mapper.UserMapper;
//import com.example.springbootmybatis.pojo.user;
//import com.example.springbootmybatis.pojo.entrustment;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.servlet.http.HttpSession;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@Controller
//public class AcceptCommissionController {
//    @Autowired
//    UserMapper mapper;
//
//    @Autowired
//    EntrustmentMapper mapper2;
//
//    @RequestMapping("/acceptcommission")
//    public String acceptcommission(@RequestParam("eid") Integer eid,
//                                   HttpSession session){
//
//
//        System.out.println("cond");
//        int i = mapper2.updateCond1(eid);
//        return "dashboard";
//    }
//}
