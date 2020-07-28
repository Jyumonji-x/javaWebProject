package com.project.service.impl;

import com.project.dao.UserDao;
import com.project.dao.impl.UserDaoImpl;
import com.project.pojo.TravelUser;
import com.project.service.RegisterService;

public class RegisterServiceImpl implements RegisterService {
    private UserDao userDao= new UserDaoImpl();

    @Override
    public void createUser(String userName, String email, String pass) {
        TravelUser travelUser = new TravelUser();
        travelUser.setUserName(userName);
        travelUser.setEmail(email);
        travelUser.setPass(pass);
        userDao.createUser(travelUser);
    }

    @Override
    public boolean hasSameUser(String userName,String email) {
        return (userDao.hasSameUser(userName)||userDao.hasSameUser(email));
    }
}
