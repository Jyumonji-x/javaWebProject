package com.project.service;

import com.project.pojo.TravelImage;

import java.util.List;

public interface MyPhotoService {
    public List<TravelImage> showAll(int uid);
    public void delete(int ImageID);
    public List<TravelImage> showAllByPage(int uid,int page);
}
