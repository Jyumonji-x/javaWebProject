package com.project.dao;

import com.project.pojo.Friend;

import java.util.List;

public interface FriendsDao {
    public List<Friend> showAllByUserUid1(int uid1);
    public void setNewFriends(int uid1,int uid2);
    public void deleteFriends(int uid1,int uid2);
    //请求添加好友
    public void order(int uid1, int uid2);
    //判断是否有添加请求
    public List<Friend> hasOrder(int uid1);
    //判断是不是已经为好友了
    public boolean hasFriend(int uid1,int uid2);
}
