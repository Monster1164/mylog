package com.cb.myblog.dao;


import com.cb.myblog.pojo.User;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
      User findByUsernameAndPassword(@Param("username") String username , @Param("password") String password);

      User getzUser();
}
