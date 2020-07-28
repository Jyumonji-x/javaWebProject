package com.project.service.impl;
import com.project.dao.ImageDao;
import com.project.dao.ImageFavorDao;
import com.project.dao.impl.ImageDaoImpl;
import com.project.dao.impl.ImageFavorDaoImpl;
import com.project.pojo.TravelImage;
import com.project.service.SearchService;
import com.project.util.Consts;
import com.project.util.Sort;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SearchServiceImpl implements SearchService {
    private ImageDao imageDao = new ImageDaoImpl();
    private ImageFavorDao imageFavorDao = new ImageFavorDaoImpl();
    private List<TravelImage> popSorting(List<TravelImage> travelImages) {
        Map map = new LinkedHashMap<>();
        if (travelImages != null) {
            for (TravelImage travelImage : travelImages) {
                int count = 0;
                if (imageFavorDao.getImageFavorListByImageID(travelImage.getImageID()) != null) {
                    count = imageFavorDao.getImageFavorListByImageID(travelImage.getImageID()).size();
                }
                map.put(travelImage.getImageID(), count);
            }
            List<Map.Entry<Integer, Integer>> list = Sort.sortByValueFloatDesc(map);
            List<TravelImage> travelImagesReturn = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                travelImagesReturn.add(imageDao.getImageById(list.get(i).getKey()));
            }
            return travelImagesReturn;
        }
        return null;
    }
    @Override
    public List<TravelImage> ImagesTitleSearchByPopSplitPage(String title,int page) {
        List<TravelImage> travelImages = imageDao.searchByTitleOrderByPopSplitPage(title,page);
        return popSorting(travelImages);
    }

    @Override
    public List<TravelImage> ImagesDescriptionSearchByPopSplitPage(String description,int page) {
        List<TravelImage> travelImages = imageDao.searchByDescriptionOrderByPopSplitPage(description,page);

        return popSorting(travelImages);
    }

    @Override
    public List<TravelImage> ImagesTitleSearchByTimeSplitPage(String title,int page) {
        return imageDao.searchByTitleOrderByTimeSplitPage(title,page);
    }

    @Override
    public List<TravelImage> ImagesDescriptionSearchByTimeSplitPage(String description,int page) {
        return imageDao.searchByDescriptionOrderByTimeSplitPage(description,page);
    }

    @Override
    public long countTitlePage(String title) {
        long count =  imageDao.countSearchByTitle(title);
        if(count%Consts.IMAGE_EVERY_PAGE==0){
            return count/6;
        }else{
            return count/6+1;
        }
    }
    @Override
    public long countDescriptionPage(String description) {
        long count =  imageDao.countSearchByDescription(description);
        if(count%Consts.IMAGE_EVERY_PAGE==0){
            return count/6;
        }else{
            return count/6+1;
        }
    }
}
