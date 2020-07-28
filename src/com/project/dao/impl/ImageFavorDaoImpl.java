package com.project.dao.impl;

import com.project.dao.DAO;
import com.project.dao.ImageFavorDao;
import com.project.pojo.TravelImageFavor;
import com.project.util.Consts;

import java.util.List;

public class ImageFavorDaoImpl extends DAO<TravelImageFavor> implements ImageFavorDao  {

    @Override
    public List<TravelImageFavor> getAll() {
        String sql = "SELECT FavorID,UID,ImageID FROM travelImageFavor";
        return getForList(sql);
    }

    @Override
    public List<TravelImageFavor> getImageFavorListByUserUid(int uid) {
        String sql = "SELECT FavorID,UID,ImageID FROM travelImageFavor WHERE UID = ?";
        return getForList(sql,uid);
    }

    @Override
    public List<TravelImageFavor> getImageFavorListByUserUidByPage(int uid, int page) {
        String sql = "SELECT FavorID,UID,ImageID FROM travelImageFavor WHERE UID = ? LIMIT ? , ? ;";
        return getForList(sql,uid,(page-1)* Consts.IMAGE_EVERY_PAGE,Consts.IMAGE_EVERY_PAGE);
    }

    @Override
    public List<TravelImageFavor> getImageFavorListByImageID(int imageID) {
        String sql = "SELECT FavorID,UID,ImageID FROM travelImageFavor WHERE ImageID = ?";
        return getForList(sql,imageID);
    }

    @Override
    public void saveImageFavor(TravelImageFavor travelImageFavor) {
        String sql = "INSERT INTO travelImageFavor (UID,ImageID) VALUES (?,?)";
        update(sql,travelImageFavor.getUid(),travelImageFavor.getImageId());

    }

    @Override
    public void deleteImageFavorByUidAndImageId(int uid,int imageID) {
        String sql ="DELETE FROM travelImageFavor WHERE UID = ? AND ImageID = ?";
        update(sql,uid,imageID);
    }
    @Override
    public void deleteImageFavorByImageId(int imageID){
        String sql ="DELETE FROM travelImageFavor WHERE ImageID = ?";
        update(sql,imageID);
    }
    @Override
    public boolean hasImageFavorByUidAndImageId(int uid, int imageID) {
        String sql ="SELECT UID FROM travelImageFavor WHERE UID = ? AND ImageID = ?";
        return get(sql,uid,imageID)!=null;
    }
}
