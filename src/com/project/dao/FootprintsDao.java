package com.project.dao;

import com.project.pojo.Footprint;

import java.util.List;

public interface FootprintsDao {
//    ȡǰʮ��
    public List<Footprint> showTenByUserUid(int uid);
    public void addFootprint(int uid,int imageId);
    public boolean hasFootprint(int uid, int imageId);
// ɾ������ʮ���Ĳ���
    public void deletePast(int uid);
    public void deleteFootprint(int uid,int imageId);
    public List<Footprint> showAllByUserUid(int uid);
    public void deleteImage(int imageId);
}
