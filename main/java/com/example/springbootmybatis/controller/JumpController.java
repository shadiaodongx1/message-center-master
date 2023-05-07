package com.example.springbootmybatis.controller;


import com.example.springbootmybatis.mapper.AdminMapper;
import com.example.springbootmybatis.mapper.UserMapper;
import com.example.springbootmybatis.mapper.EntrustmentMapper;
import com.example.springbootmybatis.pojo.admin;
import com.example.springbootmybatis.pojo.user;
import com.example.springbootmybatis.pojo.entrustment;
import com.example.springbootmybatis.service.UserService;
import com.example.springbootmybatis.service.EntrustmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class JumpController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EntrustmentMapper entrustmentMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private EntrustmentService entrustmentService;

    @RequestMapping("/jumptomain")
    public String  tomain(){
        return "dashboard";
    }

    @RequestMapping("/jumptoindex")
    public String  toindex(){
        return "index";
    }

//    @RequestMapping("*")
//    public String  to404(){
//        return "404";
//    }

    @RequestMapping("/jumptobindmail")
    public String  tobindmail(){
        return "operate/BindMail";
    }

    @RequestMapping("/jumptosendmail")
    public String  tosendmail(){
        return "operate/SendEmail";
    }

//    @RequestMapping("/jumptofindpwd")
//    public String  tofindpwd(){
//        return "operate/findpwd";
//    }

//    @RequestMapping("/jumptopwdret")
//    public String  jumptopwdret(){
//        return "PasswordRetrieval";
//    }
    @ResponseBody
    @RequestMapping("/jumptoforgetpwd")
    public String  toforgetpwd(){
        return "forgetpwd";
    }

    @ResponseBody
    @RequestMapping("/jumptoresetpwd")
    public String  toresetpwd(){
        return "resetpwd";
    }


    @RequestMapping("/jumptouserlist")
    public String touserlist(Model model, HttpSession session){

        List<user> users = userMapper.queryUserList();
        System.out.println(users);
        String userwho = (String) session.getAttribute("userwho");
        String userpwd = (String) session.getAttribute("userpwd");
        String md5userpwd = DigestUtils.md5DigestAsHex(userpwd.getBytes(StandardCharsets.UTF_8));
        admin admin = adminMapper.queryAdminByNP(userwho, md5userpwd);

        if (admin==null) {
            model.addAttribute("UserList", users);
            model.addAttribute("mespermission","你是管理员，为你展示全部权限");
            return "operate/userlist";
        }
        else {
            model.addAttribute("error1","不是管理员，权限不足");
            //return "redirect:/jumptochakanliuyan";   //不是管理员转到查看
            return "operate/chakan";
        }
    }

    @RequestMapping("/jumptocomment")
    public String ToComment(@RequestParam("eid") int eid,Model model){
        entrustment entrustment = entrustmentMapper.queryentrustmentByEid(eid);
        model.addAttribute("entrustment",entrustment);
        System.out.println("git");
        return "operate/comment";
    }

    @RequestMapping("/jumptoacceptcommission")
    public String ToAcceptCommission(@RequestParam("eid") int eid,Model model){
        entrustment entrustment = entrustmentMapper.queryentrustmentByEid(eid);
        model.addAttribute("entrustment",entrustment);
        entrustmentMapper.updateCond1(eid);
        System.out.println(eid);
        System.out.println("ok");
        return "redirect:/jumptochakanliuyan";
    }

    @PostMapping("/jumptochakanliuyan")
    @RequestMapping("/jumptochakanliuyan")
    public String chakanfenye(@RequestParam("keyword") String keyword,ModelMap map,Model model){

//        List<entrustment> entrustment = entrustmentMapper.queryEntrustmentList();
        List<entrustment> entrustment = entrustmentMapper.findByEntrustmentContaining(keyword);

        model.addAttribute("EntrustmentList",entrustment);
        return "operate/chakan";




//        @RequestParam(defaultValue = "1") int pageNum,
//        @RequestParam(defaultValue = "5") int pageSize,

//        System.out.println(pageNum);
//        System.out.println(pageSize);
//        //把查询的表给前端
//        List<user> users = userService.queryUserListByPage(pageNum,pageSize);
//        map.addAttribute("UserList",users);
//
//        //实现分页功能
//        PageInfo<user> userPageInfo = new PageInfo<>(users);
//        model.addAttribute("pageInfo",userPageInfo);
//        return "/operate/chakan";

    }
//    public String ToChaKanLiuYan(Model model){
//        List<user> users = userMapper.queryUserList();
//        System.out.println(users);
//        model.addAttribute("UserList",users);
//        return "/operate/chakan";


//    }

//    //尝试使用分页查询
//    @RequestMapping("/page")
//    public String page(@RequestParam(defaultValue = "1") int pageNum,
//                       @RequestParam(defaultValue = "5") int pageSize,
//                       ModelMap map,Model model){
//        System.out.println(pageNum);
//        System.out.println(pageSize);
//        //把查询的表给前端
//        List<user> users = userService.queryUserListByPage(pageNum,pageSize);
//        map.addAttribute("UserList",users);
//
//        //实现分页功能
//        List<user> users1 = userService.queryUserListByPage(pageNum, pageSize);
//        PageInfo<user> userPageInfo = new PageInfo<>(users1);
//        model.addAttribute("pageInfo",userPageInfo);
//        return "/operate/chakan";
//
//    }

    @RequestMapping("/jumptoliuyan")
    public String toLiuYan(){
        return "operate/liuyan";
    }

    @RequestMapping("/jumptoadminregister")
    public String jumptoadminregister(){
        return "operate/adminregister";
    }

    @RequestMapping("/jumptochangepwd")
    public String jumptochangepwd(){
        return "operate/changepwd";
    }

    @ResponseBody
    @RequestMapping("/jiekou")
    public List<user> jiekou(){
        return userMapper.queryUserList();
    }

    @ResponseBody
    @RequestMapping("/jiekou2")
    public List<entrustment> jiekou2(){
        return entrustmentMapper.queryEntrustmentList();
    }


//    @RequestMapping("/jumptochakanliuyan")
//    @PostMapping("/jumptochakanliuyan")
//    public String getEntrustmentList(@RequestParam("keyword") String keyword, Model model) {
//        System.out.println(keyword);
//        List<entrustment> entrustments = entrustmentMapper.findByEntrustmentContaining(keyword);
//
//        System.out.println(entrustments);
//        System.out.println(entrustmentMapper.findByEntrustmentContaining("sad"));
//
//        model.addAttribute("entrustmentList", entrustments);
//        return "operate/chakan";
//    }


}
