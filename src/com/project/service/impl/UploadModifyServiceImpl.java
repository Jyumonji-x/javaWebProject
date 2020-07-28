package com.project.service.impl;

import com.project.dao.ImageDao;
import com.project.dao.impl.ImageDaoImpl;
import com.project.pojo.TravelImage;
import com.project.service.UploadModifyService;

public class UploadModifyServiceImpl implements UploadModifyService {
    ImageDao imageDao = new ImageDaoImpl();
    @Override
    public void modify(TravelImage travelImage) {
        imageDao.updateImage(travelImage.getImageID(),travelImage);
    }
}
