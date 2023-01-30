package com.bnuz.service.impl;

import com.bnuz.dao.UserDao;
import com.bnuz.dao.impl.UserDaoImpl;
import com.bnuz.pojo.User;
import com.bnuz.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String usernaem) {
        if(userDao.queryUserByUsername(usernaem) == null){
            return false;
        }
        return true;
    }
}
