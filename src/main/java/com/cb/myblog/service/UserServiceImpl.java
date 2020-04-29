package com.cb.myblog.service;

import com.cb.myblog.dao.UserDao;
import com.cb.myblog.pojo.User;
import com.cb.myblog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao =null;

    @Override
    public User checkUser(String username, String password) {
        User user = userDao.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }

    @Override
    public User getUser() {
        return userDao.getzUser();
    }
}
