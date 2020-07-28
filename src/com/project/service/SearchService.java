package com.project.service;

import com.project.pojo.TravelImage;

import java.util.List;

public interface SearchService {
    public List<TravelImage> ImagesTitleSearchByPopSplitPage(String title,int page);
    public List<TravelImage> ImagesDescriptionSearchByPopSplitPage(String description,int page);
    public List<TravelImage> ImagesTitleSearchByTimeSplitPage(String title,int page);
    public List<TravelImage> ImagesDescriptionSearchByTimeSplitPage(String description,int page);
    public long countTitlePage(String title);
    public long countDescriptionPage(String description);
}
