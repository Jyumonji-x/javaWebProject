package com.project.service.impl;

import com.project.dao.FootprintsDao;
import com.project.dao.ImageDao;
import com.project.dao.ImageFavorDao;
import com.project.dao.UserDao;
import com.project.dao.impl.FootprintsDaoImpl;
import com.project.dao.impl.ImageDaoImpl;
import com.project.dao.impl.ImageFavorDaoImpl;
import com.project.dao.impl.UserDaoImpl;
import com.project.pojo.Footprint;
import com.project.pojo.TravelImage;
import com.project.pojo.TravelImageFavor;
import com.project.service.FavouriteService;

import java.util.ArrayList;
import java.util.List;

public class FavouriteServiceImpl implements FavouriteService {
    private FootprintsDao footprintsDao = new FootprintsDaoImpl();
    private ImageFavorDao imageFavorDao = new ImageFavorDaoImpl();
    private ImageDao imageDao = new ImageDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    @Override
    public void setPermission(int uid,boolean permission){
        userDao.setPermission(uid,permission);
    }
    @Override
    public boolean getPermission(int uid){
        return userDao.getByUid(uid).isPermission();
    }
    @Override
    public List<TravelImage> showFavors(int uid) {
        List<TravelImage> travelImages = new ArrayList<>();
        for (TravelImageFavor travelImageFavor:imageFavorDao.getImageFavorListByUserUid(uid)){
            travelImages.add(imageDao.getImageById(travelImageFavor.getImageId()));
        }
        return travelImages;
    }
    @Override
    public List<TravelImage> showFavorsByPage(int uid,int page){
        List<TravelImage> travelImages = new ArrayList<>();
        for (TravelImageFavor travelImageFavor:imageFavorDao.getImageFavorListByUserUidByPage(uid,page)){
            travelImages.add(imageDao.getImageById(travelImageFavor.getImageId()));
        }
        return travelImages;
    }
    @Override
    public List<TravelImage> showFootprints(int uid) {
        List<TravelImage> travelImages = new ArrayList<>();
        for(Footprint footprint:footprintsDao.showTenByUserUid(uid)){
            travelImages.add(imageDao.getImageById(footprint.getImageId()));
        };
        return travelImages;
    }

    @Override
    public void delete(int uid,int imageID) {
        imageFavorDao.deleteImageFavorByUidAndImageId(uid, imageID);
    }
}
