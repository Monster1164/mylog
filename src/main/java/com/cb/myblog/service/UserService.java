package com.cb.myblog.service;

import com.cb.myblog.pojo.User;


public interface UserService {

     User checkUser(String username,String password);
     User getUser();
}
