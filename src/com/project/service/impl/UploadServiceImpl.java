package com.project.service.impl;

import com.project.dao.GeoCitiesDao;
import com.project.dao.GeoCountriesDao;
import com.project.dao.ImageDao;
import com.project.dao.impl.GeoCitiesDaoImpl;
import com.project.dao.impl.GeoCountriesDaoImpl;
import com.project.dao.impl.ImageDaoImpl;
import com.project.pojo.GeoCities;
import com.project.pojo.GeoCountries;
import com.project.pojo.TravelImage;
import com.project.service.UploadService;

import java.util.List;

public class UploadServiceImpl implements UploadService {
    private ImageDao imageDao = new ImageDaoImpl();
    private GeoCountriesDao geoCountriesDao = new GeoCountriesDaoImpl();
    private GeoCitiesDao geoCitiesDao = new GeoCitiesDaoImpl();
    @Override
    public void upload(TravelImage travelImage) {
        imageDao.saveImage(travelImage);
    }
    @Override
    public List<GeoCities> showCities(String iso){
        return geoCitiesDao.getCitiesByCountryISO(iso);
    }
    @Override
    public List<GeoCountries> showCountries(){
        return geoCountriesDao.getAll();
    }
    @Override
    public TravelImage getImageById(int id){
        return imageDao.getImageById(id);
    }
}
