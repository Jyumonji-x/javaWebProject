package com.project.dao;

import com.project.pojo.TravelImage;
import java.util.List;

public interface ImageDao {
    public List<TravelImage> getAll();
    public boolean hasImageById(int imageID);
    public TravelImage getImageById(int imageID);
    public void saveImage(TravelImage travelImage);
    public void deleteImageById(int imageID);
    public List<TravelImage> getNewImages(int number);
    public List<TravelImage> getUserImage(int uid);
    public List<TravelImage> getUserImageByPage(int uid,int page);
    //已经实现多关键词搜索
    public List<TravelImage> searchByTitleOrderByPopSplitPage(String title,int page);
    public List<TravelImage> searchByTitleOrderByTimeSplitPage(String title,int page);
    public long countSearchByTitle(String title);
    //已经实现多关键词搜索
    public List<TravelImage> searchByDescriptionOrderByPopSplitPage(String description,int page);
    public List<TravelImage> searchByDescriptionOrderByTimeSplitPage(String description,int page);
    public long countSearchByDescription(String description);
    public void updateImage(int imageId,TravelImage travelImage);
}
