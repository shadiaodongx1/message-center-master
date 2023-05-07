package com.example.springbootmybatis.mapper;
import com.example.springbootmybatis.pojo.user;
import org.apache.ibatis.annotations.Mapper;


import com.example.springbootmybatis.pojo.admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {

   admin  queryAdminByNP(String adminname,String adminpwd);

    public int addAdmin(admin admin);
}
