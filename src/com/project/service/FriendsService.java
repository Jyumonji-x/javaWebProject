package com.project.service;

import com.project.pojo.TravelUser;

import java.util.List;

public interface FriendsService {
    public List<TravelUser> showAllByUid(int uid);
    public void setPermission(int uid, boolean canReach);
    public void order(int uid1,int uid2);
}
