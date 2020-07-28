package com.project.service;

import com.project.pojo.TravelImage;

import java.util.List;

public interface FavouriteService {
    public void setPermission(int uid,boolean permission);
    public boolean getPermission(int uid);
    public List<TravelImage> showFavors(int uid);
    public List<TravelImage> showFavorsByPage(int uid,int page);
    public List<TravelImage> showFootprints(int uid);
    public void delete(int uid,int imageID);

}
