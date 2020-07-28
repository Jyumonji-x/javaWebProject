package com.project.service.impl;

import com.project.dao.ImageDao;
import com.project.dao.ImageFavorDao;
import com.project.dao.impl.ImageDaoImpl;
import com.project.dao.impl.ImageFavorDaoImpl;
import com.project.pojo.TravelImage;
import com.project.pojo.TravelImageFavor;
import com.project.service.IndexService;
import com.project.util.Sort;

import java.util.*;

public class IndexServiceImpl implements IndexService {
    private ImageFavorDao imageFavorDao = new ImageFavorDaoImpl();
    private ImageDao imageDao = new ImageDaoImpl();
    @Override
    public List<TravelImage> popularPhotos() {
        List<TravelImageFavor> imageFavors = imageFavorDao.getAll();
        Map<Integer,Integer> map = new LinkedHashMap<Integer,Integer>();
        for(TravelImageFavor travelImageFavor:imageFavors){
            if(map.containsKey(travelImageFavor.getImageId())){
                map.put(travelImageFavor.getImageId(),map.get(travelImageFavor.getImageId())+1);
            }
            else{
                map.put(travelImageFavor.getImageId(),1);
            }
        }
        List<Map.Entry<Integer,Integer>> list = Sort.sortByValueFloatDesc(map);
        List<TravelImage> travelImages = new ArrayList<TravelImage>();
        for(int i =0;i<4;i++){
            travelImages.add(imageDao.getImageById(list.get(i).getKey()));
        }
        return travelImages;
    }

    @Override
    public List<TravelImage> newPhotos() {
        return imageDao.getNewImages(4);
    }
}
