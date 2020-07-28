package com.project.service.impl;

import com.project.dao.UserDao;
import com.project.dao.impl.UserDaoImpl;
import com.project.service.LoginService;

public class LoginServiceImpl implements LoginService {
    private UserDao userDao= new UserDaoImpl();
    @Override
    public boolean isUser(String userNameOrEmail, String pass) {
        return userDao.isUserByInfo(userNameOrEmail,pass);
    }

    @Override
    public int getUserUid(String userNameOrEmail, String pass) {
        return userDao.getUidByUserNameOrEmail(userNameOrEmail,pass);
    }
}
