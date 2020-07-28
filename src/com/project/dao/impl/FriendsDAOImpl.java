package com.project.dao.impl;

import com.project.dao.DAO;
import com.project.dao.FriendsDao;
import com.project.pojo.Friend;
import com.sun.corba.se.impl.ior.FreezableList;

import java.util.ArrayList;
import java.util.List;

public class FriendsDAOImpl extends DAO<Friend> implements FriendsDao {
    @Override
    public List<Friend> showAllByUserUid1(int uid1) {
        String sql = "SELECT uid1,uid2 FROM friends WHERE uid1 = ?";
        List<Friend> friends = new ArrayList<>();
        for(Friend friend :getForList(sql,uid1)){
            if(hasFriend(uid1,friend.getUid2())) friends.add(friend);
        }
        return friends;
    }

    @Override
    public void setNewFriends(int uid1, int uid2) {
        String sql = "INSERT INTO friends (UID1, UID2) VALUES (?,?)";
        update(sql,uid1,uid2);
        update(sql,uid2,uid1);
    }

    @Override
    public void deleteFriends(int uid1, int uid2) {
        String sql = "DELETE FROM friends WHERE UID1 = ? AND UID2 = ?";
        update(sql,uid1,uid2);
        update(sql,uid2,uid1);
    }

    @Override
    public void order(int uid1, int uid2) {
        String sql1 = "SELECT * FROM friends WHERE UID1 = ? AND UID2 = ? ;";
        if(getForList(sql1,uid1,uid2)==null||getForList(sql1,uid1,uid2).size()==0){
            String sql = "INSERT INTO friends (UID1, UID2) VALUES (?,?)";
            update(sql,uid1,uid2);
        }
    }

    @Override
    public List<Friend> hasOrder(int uid2) {
        String sql = "SELECT uid1,uid2 FROM friends WHERE uid2 = ?";
        List<Friend> friends = new ArrayList<>();
        for(Friend friend : getForList(sql,uid2)){
            if(!hasFriend(uid2,friend.getUid1()))
                friends.add(friend);
        }
        return friends;
    }

    @Override
    public boolean hasFriend(int uid1, int uid2) {
        String sql = "SELECT uid1,uid2 FROM friends WHERE uid1 = ? AND uid2 = ?";
        return getForList(sql, uid1, uid2).size() == 1 && getForList(sql, uid2, uid1).size() == 1;
    }
}
