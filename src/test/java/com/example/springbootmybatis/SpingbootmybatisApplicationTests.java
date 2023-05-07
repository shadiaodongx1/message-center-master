package com.example.springbootmybatis;

import com.example.springbootmybatis.mapper.AdminMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class SpingbootmybatisApplicationTests {

    @Autowired
    DataSource dataSource;
    @Autowired
    private AdminMapper adminMapper;
    @Test
    void contextLoads() throws SQLException {
//        System.out.println(dataSource.getClass());
//        System.out.println(dataSource.getConnection());

        System.out.println(adminMapper.queryAdminByNP("admin","123456"));
    }

}
