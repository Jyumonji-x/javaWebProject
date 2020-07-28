package com.project.dao;

import com.project.pojo.Friend;

import java.util.List;

public interface FriendsDao {
    public List<Friend> showAllByUserUid1(int uid1);
    public void setNewFriends(int uid1,int uid2);
    public void deleteFriends(int uid1,int uid2);
    //������Ӻ���
    public void order(int uid1, int uid2);
    //�ж��Ƿ����������
    public List<Friend> hasOrder(int uid1);
    //�ж��ǲ����Ѿ�Ϊ������
    public boolean hasFriend(int uid1,int uid2);
}
