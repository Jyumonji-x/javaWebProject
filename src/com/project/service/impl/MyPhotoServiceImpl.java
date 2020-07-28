package com.project.service.impl;

import com.project.dao.ImageDao;
import com.project.dao.ImageFavorDao;
import com.project.dao.impl.ImageDaoImpl;
import com.project.dao.impl.ImageFavorDaoImpl;
import com.project.pojo.TravelImage;
import com.project.service.MyPhotoService;

import java.util.List;

public class MyPhotoServiceImpl implements MyPhotoService {
    private ImageDao imageDao = new ImageDaoImpl();
    private ImageFavorDao imageFavorDao = new ImageFavorDaoImpl();
    @Override
    public List<TravelImage> showAll(int uid) {
        return imageDao.getUserImage(uid);
    }

    @Override
    public void delete(int imageID) {
        imageDao.deleteImageById(imageID);
        imageFavorDao.deleteImageFavorByImageId(imageID);
    }

    @Override
    public List<TravelImage> showAllByPage(int uid,int page){
        return imageDao.getUserImageByPage(uid,page);
    }
}
