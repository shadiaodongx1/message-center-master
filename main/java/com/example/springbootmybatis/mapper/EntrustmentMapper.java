package com.example.springbootmybatis.mapper;


import com.example.springbootmybatis.pojo.entrustment;
import com.example.springbootmybatis.pojo.user;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//这个注解表示了这是一个mybatis的mapper类
@Mapper
public interface EntrustmentMapper {

     List<entrustment> queryEntrustmentList();
//这里有什么问题？？
    //发布委托
     int addEntrustment(String username, String entrustment, String cond);

     int updateComment(int eid,String comment);

     entrustment queryentrustmentByName(String username);

     entrustment queryentrustmentByEid(int eid);

     int updateCond1(int eid);

     List<entrustment> findByEntrustmentContaining(String keyword);
}
