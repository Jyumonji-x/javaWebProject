package com.project.service;

import com.project.pojo.TravelImage;

import java.util.List;

public interface IndexService {
    public List<TravelImage> popularPhotos();
    public List<TravelImage> newPhotos();
}
