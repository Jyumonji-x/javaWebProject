package com.project.service;

import com.project.pojo.TravelImage;

public interface DetailsService {
    public void addFootprint(int imageId,int uid);
    public TravelImage showImage(int imageId);
    public boolean likeOrNot(int imageId,int uid);
    public void like(int imageId,int uid);
    public void dislike(int imageId,int uid);
    public int likePeople(int imageId);
    public String countryName(String countryCode);
    public String cityName(int cityCode);
    public String userName(int uid);
}
