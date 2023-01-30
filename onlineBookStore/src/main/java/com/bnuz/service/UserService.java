package com.bnuz.service;

import com.bnuz.pojo.User;

public interface UserService {
    public void registUser(User user);
    public User login(User user);
    public boolean existsUsername(String usernaem);

}
