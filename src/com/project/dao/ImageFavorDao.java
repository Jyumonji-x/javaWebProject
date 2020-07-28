package com.project.dao;

import com.project.pojo.TravelImageFavor;

import java.util.List;

public interface ImageFavorDao {
    public List<TravelImageFavor> getAll();
    public List<TravelImageFavor> getImageFavorListByUserUid(int uid);
    public List<TravelImageFavor> getImageFavorListByUserUidByPage(int uid,int page);
    public List<TravelImageFavor> getImageFavorListByImageID(int imageID);
    public void saveImageFavor(TravelImageFavor travelImageFavor);
    public void deleteImageFavorByUidAndImageId(int uid,int imageID);
    public void deleteImageFavorByImageId(int imageID);
    public boolean hasImageFavorByUidAndImageId(int uid, int imageID);
}
