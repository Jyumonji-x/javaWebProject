package com.project.service.impl;

import com.project.dao.*;
import com.project.dao.impl.*;
import com.project.pojo.GeoCities;
import com.project.pojo.TravelImage;
import com.project.pojo.TravelImageFavor;
import com.project.pojo.TravelUser;
import com.project.service.DetailsService;

public class DetailsServiceImpl implements DetailsService {
    private FootprintsDao footprintsDao = new FootprintsDaoImpl();
    private ImageDao imageDao = new ImageDaoImpl();
    private ImageFavorDao imageFavorDao = new ImageFavorDaoImpl();
    private GeoCitiesDao geoCitiesDao = new GeoCitiesDaoImpl();
    private GeoCountriesDao geoCountriesDao = new GeoCountriesDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    @Override
    public void addFootprint(int imageId,int uid){
        if(footprintsDao.hasFootprint(uid,imageId)){
            footprintsDao.deleteFootprint(uid,imageId);
        }
        while(footprintsDao.showAllByUserUid(uid).size()>10){
            footprintsDao.deletePast(uid);
        }
        footprintsDao.addFootprint(uid,imageId);
    }
    @Override
    public TravelImage showImage(int imageId) {

        return imageDao.getImageById(imageId);

    }

    @Override
    public boolean likeOrNot(int imageId, int uid) {

        return imageFavorDao.hasImageFavorByUidAndImageId(uid,imageId);
    }

    @Override
    public void like(int imageId, int uid) {
        TravelImageFavor travelImageFavor = new TravelImageFavor();
        travelImageFavor.setUid(uid);
        travelImageFavor.setImageId(imageId);
        imageFavorDao.saveImageFavor(travelImageFavor);
    }

    @Override
    public void dislike(int imageId, int uid) {
        imageFavorDao.deleteImageFavorByUidAndImageId(uid,imageId);
    }

    @Override
    public int likePeople(int imageId) {
        try{
        return imageFavorDao.getImageFavorListByImageID(imageId).size();
        }catch (NullPointerException e){
            return 0;
        }
    }

    @Override
    public String countryName(String countryCode) {
        try {
            return geoCountriesDao.getCountryByIso(countryCode).getCountry_regionName();
        }catch (NullPointerException e){
            return null;
        }
    }

    @Override
    public String cityName(int cityCode) {
        try{
        return geoCitiesDao.getCityById(cityCode).getAsciiName();
        }catch (NullPointerException e){
            return null;
        }
    }
    @Override
    public String userName(int uid){
        try {
            return userDao.getByUid(uid).getUserName();
        }catch (NullPointerException e){
        return null;
    }
    }
}