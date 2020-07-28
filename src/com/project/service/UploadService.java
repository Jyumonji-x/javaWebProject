package com.project.service;

import com.project.pojo.GeoCities;
import com.project.pojo.GeoCountries;
import com.project.pojo.TravelImage;

import java.util.List;

public interface UploadService {
    public void upload(TravelImage travelImage);
    public List<GeoCities> showCities(String iso);
    public List<GeoCountries> showCountries();
    public TravelImage getImageById(int id);

}
