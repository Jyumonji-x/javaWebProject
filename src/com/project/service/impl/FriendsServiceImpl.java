package com.project.service.impl;

import com.project.dao.FriendsDao;
import com.project.dao.UserDao;
import com.project.dao.impl.FriendsDAOImpl;
import com.project.dao.impl.UserDaoImpl;
import com.project.pojo.Friend;
import com.project.pojo.TravelUser;
import com.project.service.FriendsService;

import java.util.ArrayList;
import java.util.List;

public class FriendsServiceImpl implements FriendsService {
    private FriendsDao friendsDAO = new FriendsDAOImpl();
    private UserDao userDao = new UserDaoImpl();
    @Override
    public List<TravelUser> showAllByUid(int uid) {
        List<Friend> friends = friendsDAO.showAllByUserUid1(uid);
        List<TravelUser> travelUsers = new ArrayList<>();
        for(Friend friend:friends){
            travelUsers.add(userDao.getByUid(friend.getUid2()));
        }
        return travelUsers;
    }

    @Override
    public void setPermission(int uid, boolean canReach) {
        userDao.setPermission(uid,canReach);
    }
    @Override
    public void order(int uid1,int uid2){
        friendsDAO.order(uid1,uid2);
    }
}
