package com.cb.myblog;

import com.cb.myblog.aspect.LogAspect;
import com.cb.myblog.dao.UserDao;
import org.apache.coyote.OutputBuffer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@MapperScan(basePackageClasses = {UserDao.class},annotationClass = Repository.class)
public class MylogApplication {


//    @Bean("logaspect")
//    public LogAspect logAspect(){
//        return  new LogAspect();
//    }
    public static void main(String[] args) {
        SpringApplication.run(MylogApplication.class, args);
    }

}
