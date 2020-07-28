package com.project.dao.impl;

import com.project.dao.DAO;
import com.project.dao.ImageDao;
import com.project.pojo.TravelImage;
import com.project.util.Consts;

import java.util.ArrayList;
import java.util.List;

public class ImageDaoImpl extends DAO<TravelImage> implements ImageDao {
    public static void main(String[] args) {
        System.out.print(new ImageDaoImpl().hasImageById(88));
    }
    @Override
    public List<TravelImage> getAll() {
        String sql = "SELECT ImageID,Title,Description,CityCode,Country_RegionCodeISO,UID,PATH,Content,LastModifiedTime FROM travelImage ;";
        return getForList(sql);
    }
    @Override
    public boolean hasImageById(int imageID) {
        String sql = "SELECT ImageID,Title,Description,CityCode,Country_RegionCodeISO,UID,PATH,Content,LastModifiedTime FROM travelImage WHERE ImageID = ? ;";
        return getForList(sql,imageID)!=null&&getForList(sql,imageID).size()!=0;
    }
    @Override
    public TravelImage getImageById(int imageID) {
        String sql = "SELECT ImageID,Title,Description,CityCode,Country_RegionCodeISO,UID,PATH,Content,LastModifiedTime FROM travelImage WHERE ImageID = ? ;";
        return get(sql,imageID);
    }

    @Override
    public void saveImage(TravelImage travelImage) {
        String sql = "INSERT INTO travelImage (Title,Description,CityCode,Country_RegionCodeISO,UID,PATH,Content) VALUES (?,?,?,?,?,?,?) ;";
        update(sql,travelImage.getTitle(),travelImage.getDescription(),travelImage.getCityCode(),travelImage.getCountry_RegionCodeISO(),travelImage.getUid(),travelImage.getPath(),travelImage.getContent());
    }
    @Override
    public void deleteImageById(int imageID) {
        String sql ="DELETE FROM travelImage WHERE ImageID = ?";
        update(sql,imageID);
    }

    @Override
    public List<TravelImage> getNewImages(int number) {
        String sql = "SELECT ImageID,Title,Description,CityCode,Country_RegionCodeISO,UID,PATH,Content,LastModifiedTime FROM travelImage ORDER BY LastModifiedTime DESC LIMIT ? ;";
        return getForList(sql,number);
    }

    @Override
    public List<TravelImage> getUserImage(int uid) {
        String sql = "SELECT ImageID,Title,Description,CityCode,Country_RegionCodeISO,UID,PATH,Content,LastModifiedTime FROM travelImage WHERE UID = ? ;";
        return getForList(sql,uid);
    }

    @Override
    public List<TravelImage> getUserImageByPage(int uid, int page) {
        String sql = "SELECT ImageID,Title,Description,CityCode,Country_RegionCodeISO,UID,PATH,Content,LastModifiedTime FROM travelImage WHERE UID = ? LIMIT ? , ? ;";
        return getForList(sql,uid,(page-1)*Consts.IMAGE_EVERY_PAGE,Consts.IMAGE_EVERY_PAGE);
    }

    @Override
    public List<TravelImage> searchByTitleOrderByPopSplitPage(String title,int page) {
        String[] titlesSplit=title.split(" ");
        ArrayList<String > titles = new ArrayList<>();
        for( int i = 0;i<titlesSplit.length;i++){
            if(!titlesSplit[i].equals("")) titles.add(titlesSplit[i]);
        }
        if(titles.size()==0) return null;
        String sql2 = "SELECT travelImage.ImageID,Title,Description,CityCode,Country_RegionCodeISO,travelImage.UID,PATH,Content,LastModifiedTime,likePeople FROM (travelImage LEFT JOIN likes ON likes.ImageID = travelImage.ImageID)  WHERE Title LIKE '%"+titles.get(0)+"%'  ";
        for( int i =1;i<titles.size();i++){
            sql2 = sql2 +" AND title like '%"+titles.get(i)+"%' ";
        }
        sql2 = sql2+" ORDER BY likePeople DESC LIMIT ? , ? ; ";
        String sql1 = "CREATE TEMPORARY TABLE likes AS SELECT *, count(*) as likePeople FROM travelImageFavor GROUP BY ImageID;";

        String sql3 = "DROP TABLE likes;";
        return updateAndGetForList(sql1,sql2,sql3,(page-1)*Consts.IMAGE_EVERY_PAGE,Consts.IMAGE_EVERY_PAGE);
    }

    @Override
    public List<TravelImage> searchByTitleOrderByTimeSplitPage(String title,int page) {
        String[] titlesSplit=title.split(" ");
        ArrayList<String > titles = new ArrayList<>();
        for( int i = 0;i<titlesSplit.length;i++){
            if(!titlesSplit[i].equals("")) titles.add(titlesSplit[i]);
        }
        if(titles.size()==0) return null;
        String sql = "SELECT ImageID,Title,Description,CityCode,Country_RegionCodeISO,UID,PATH,Content,LastModifiedTime FROM travelImage WHERE Title LIKE '%"+titles.get(0)+"%' ";
        for( int i =1;i<titles.size();i++){
            sql = sql +" AND title LIKE '%"+titles.get(i)+"%'";
        }
        sql = sql+" ORDER BY LastModifiedTime DESC limit ? , ?; ";

        return getForList(sql,(page-1)*Consts.IMAGE_EVERY_PAGE,Consts.IMAGE_EVERY_PAGE);
    }

    @Override
    public long countSearchByTitle(String title) {
        String[] titlesSplit=title.split(" ");
        ArrayList<String > titles = new ArrayList<>();
        for( int i = 0;i<titlesSplit.length;i++){
            if(!titlesSplit[i].equals("")) titles.add(titlesSplit[i]);
        }

        if(titles.size()==0) return 0;
        String sql = "SELECT COUNT(*) FROM travelImage WHERE Title LIKE +'%"+titles.get(0)+"%'";

        for(int i =1;i<titles.size();i++){
            sql = sql + " AND Title LIKE + '%"+titles.get(i)+"%'";
        }

        return getForValue(sql);
    }

    @Override
    public List<TravelImage> searchByDescriptionOrderByPopSplitPage(String description,int page) {
        String[] descriptionsSplit=description.split(" ");

        ArrayList<String > descriptions = new ArrayList<>();
        for( int i = 0;i<descriptionsSplit.length;i++){
            if(!descriptionsSplit[i].equals("")) descriptions.add(descriptionsSplit[i]);
        }
        if(descriptions.size()==0) return null;
        String sql2 = "SELECT travelImage.ImageID,Title,Description,CityCode,Country_RegionCodeISO,travelImage.UID,PATH,Content,LastModifiedTime,likePeople FROM (travelImage LEFT JOIN likes ON likes.ImageID = travelImage.ImageID)  WHERE content LIKE '%"+descriptions.get(0)+"%' ";
        for( int i =1;i<descriptions.size();i++){
            sql2 = sql2 +" AND content like '%"+descriptions.get(i)+"%' ";
        }
        sql2 = sql2+" ORDER BY likePeople DESC LIMIT ? , ? ; ";


        String sql1 = "CREATE TEMPORARY TABLE likes AS SELECT *, count(*) as likePeople FROM travelImageFavor GROUP BY ImageID;";
        String sql3 = "DROP TABLE likes;";
        return updateAndGetForList(sql1,sql2,sql3,(page-1)*Consts.IMAGE_EVERY_PAGE,Consts.IMAGE_EVERY_PAGE);
    }

    @Override
    public List<TravelImage> searchByDescriptionOrderByTimeSplitPage(String description,int page) {

        String[] descriptionsSplit=description.split(" ");

        ArrayList<String > descriptions = new ArrayList<>();
        for( int i = 0;i<descriptionsSplit.length;i++){
            if(!descriptionsSplit[i].equals("")) descriptions.add(descriptionsSplit[i]);
        }
        if(descriptions.size()==0) return null;

        String sql = "SELECT ImageID,Title,Description,CityCode,Country_RegionCodeISO,UID,PATH,Content,LastModifiedTime FROM travelImage WHERE content LIKE '%"+descriptions.get(0)+"%' ";
        for( int i =1;i<descriptions.size();i++){
            if(descriptions.get(i).equals("")) continue;
            sql = sql +" AND content LIKE '%"+descriptions.get(i)+"%'";
        }
        sql = sql+" ORDER BY LastModifiedTime DESC limit ? , ?; ";

        return getForList(sql,(page-1)*Consts.IMAGE_EVERY_PAGE,Consts.IMAGE_EVERY_PAGE);
    }

    @Override
    public long countSearchByDescription(String description) {
        String[] descriptionsSplit=description.split(" ");
        ArrayList<String > descriptions = new ArrayList<>();
        for( int i = 0;i<descriptionsSplit.length;i++){
            if(!descriptionsSplit[i].equals("")) descriptions.add(descriptionsSplit[i]);
        }

        if(descriptions.size()==0) return 0;
        String sql = "SELECT COUNT(*) FROM travelImage WHERE content LIKE +'%"+descriptions.get(0)+"%'";

        for(int i =1;i<descriptions.size();i++){
            sql = sql + " AND content LIKE + '%"+descriptions.get(i)+"%'";
        }

        return getForValue(sql);
    }

    @Override
    public void updateImage(int imageId,TravelImage travelImage) {
        String sql = "UPDATE travelImage SET Title = ? , Description = ? , CityCode = ? , Country_RegionCodeISO = ? , UID = ? , Content = ? , LastModifiedTime = CURRENT_TIMESTAMP WHERE ImageID = ? ;";
        update(sql,travelImage.getTitle(),travelImage.getDescription(),travelImage.getCityCode(),travelImage.getCountry_RegionCodeISO(),travelImage.getUid(),travelImage.getContent(),imageId);
    }
}