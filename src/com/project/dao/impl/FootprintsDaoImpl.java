package com.project.dao.impl;

import com.project.dao.DAO;
import com.project.dao.FootprintsDao;
import com.project.pojo.Footprint;

import java.util.List;

public class FootprintsDaoImpl extends DAO<Footprint> implements FootprintsDao {
//    public static void main(String[] args) {
//        FootprintsDao footprintsDao = new FootprintsDaoImpl();
//        for(Footprint footprint : footprintsDao.showTenByUserUid(4)){
//            System.out.print(footprint.getFootId()+"  ");
//            System.out.println(footprint.getImageId());
//        }
//
//    }
    @Override
    public List<Footprint> showTenByUserUid(int uid) {
        String sql = "SELECT footId , uid,ImageId FROM footprints WHERE uid = ? ORDER BY footId DESC";
        List<Footprint> list = getForList(sql,uid);
        for(int i =0 ; i<list.size();i++){
            System.out.print(list.get(i).getImageId()+"  ");
        }
        System.out.println();
        if(list.size()<10) return list;
        else return list.subList(0,10);
    }

    @Override
    public void addFootprint(int uid, int imageId) {
        String sql = "INSERT INTO footprints (uid,imageId) VALUES (?,?)";
        update(sql, uid,imageId);
    }

    @Override
    public boolean hasFootprint(int uid, int imageId) {
        String sql = "SELECT footId FROM footprints WHERE uid = ? AND imageId = ?";
        return getForList(sql,uid,imageId)!=null;
    }

    @Override
    public void deletePast(int uid) {
        String sql1 ="SELECT footId FROM footprints WHERE footId =(SELECT min(footId) FROM footprints WHERE uid = ?);";
        int footId = getForValue(sql1,uid);
        String sql2 ="DELETE FROM footprints WHERE footId = ? ";
        update(sql2,footId);
    }
    @Override
    public void deleteImage(int imageId) {
        String sql1 ="SELECT footId FROM footprints WHERE imageId = ?;";
        int footId = getForValue(sql1,imageId);
    }
    @Override
    public void deleteFootprint(int uid,int imageId) {
        String sql2 ="DELETE FROM footprints WHERE uid = ? AND imageId = ?";
        update(sql2,uid,imageId);
    }

    @Override
    public List<Footprint> showAllByUserUid(int uid) {
        String sql = "SELECT footId , uid,ImageId FROM footprints WHERE uid = ? ORDER BY footId DESC";
        return getForList(sql,uid);
    }
}
