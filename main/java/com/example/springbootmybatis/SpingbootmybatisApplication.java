package com.example.springbootmybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;


@ServletComponentScan
@SpringBootApplication

@ImportResource(locations={"classpath:myKaptcha.xml"})


public class SpingbootmybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpingbootmybatisApplication.class, args);
    }

}
