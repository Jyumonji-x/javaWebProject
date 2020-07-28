package com.project.dao.impl;

import com.project.dao.UserDao;
import com.project.dao.DAO;
import com.project.pojo.TravelUser;
import java.util.List;

public class UserDaoImpl extends DAO<TravelUser> implements UserDao {
    @Override
    public List<TravelUser> getAll() {
        String sql = "SELECT Uid,UserName,DateJoined,DateLastModified,Email,Pass,State,Permission FROM travelUser";
        return getForList(sql);
    }

    @Override
    public boolean hasSameUser(String userNameOrEmail) {
        String sql = "SELECT Uid FROM travelUser WHERE (UserName = ? OR Email = ?)";
        return get(sql,userNameOrEmail,userNameOrEmail)!=null;

    }

    @Override
    public boolean isUserByInfo(String userNameOrEmail, String pass) {
        String sql = "SELECT Uid FROM travelUser WHERE (UserName = ? OR Email = ?) AND Pass = ?";
        return get(sql,userNameOrEmail,userNameOrEmail,pass)!=null;

    }

    @Override
    public int getUidByUserNameOrEmail(String userNameOrEmail, String pass) {
        String sql = "SELECT Uid FROM travelUser WHERE (UserName = ? OR Email = ?) AND Pass = ?";
        return getForValue(sql,userNameOrEmail,userNameOrEmail,pass);
    }

    @Override
    public void createUser(TravelUser travelUser) {
        String sql = "INSERT INTO travelUser (UserName,Email,Pass,State) VALUES (?,?,?,?)";

        update(sql,travelUser.getUserName(),travelUser.getEmail(),travelUser.getPass(),1);
    }

    @Override
    public TravelUser getByUid(int uid) {
        String sql = "SELECT Uid,UserName,DateJoined,DateLastModified,Email,Pass,State,Permission FROM travelUser WHERE uid = ?";
        return get(sql,uid);
    }

    @Override
    public void setPermission(int uid, boolean permission) {
        String sql = "UPDATE travelUser SET Permission = ? WHERE uid = ? " ;
        update(sql,permission,uid);
    }

    @Override
    public List<TravelUser> searchByUserName(String userName) {
        String sql = "SELECT Uid,UserName,DateJoined,DateLastModified,Email,Pass,State,Permission FROM travelUser WHERE UserName LIKE ?" ;
        userName = "%"+userName+"%";
        return getForList(sql,userName);
    }
}
