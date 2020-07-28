package com.project.dao;

import com.project.pojo.TravelUser;

import java.util.List;

public interface UserDao {
    public List<TravelUser> getAll();
//    注册的时候用于判断
    public boolean hasSameUser(String userNameOrEmail);
    public boolean isUserByInfo(String userNameOrEmail, String pass);
    public int getUidByUserNameOrEmail(String userNameOrEmail, String pass);
    public void createUser(TravelUser user);
    public TravelUser getByUid(int uid);
    public void setPermission (int uid, boolean permission);
    public List<TravelUser> searchByUserName(String userName);
}
