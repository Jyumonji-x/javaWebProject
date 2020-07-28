package com.project.pojo;

import java.sql.Timestamp;

public class TravelImage {
    private int imageID;
    private String title;
    private String description;
    private int cityCode;
    private String country_RegionCodeISO;
    private int uid;
    private String path;
    private String content;
    private Timestamp lastModifiedTime;

    public TravelImage(int imageID, String title, String description, int cityCode, String countryCodeISO, int uid, String path, String content) {
        this.imageID = imageID;
        this.title = title;
        this.description = description;
        this.cityCode = cityCode;
        this.country_RegionCodeISO = countryCodeISO;
        this.uid = uid;
        this.path = path;
        this.content = content;
    }
    public void setLastModifiedTime(Timestamp lastModifiedTime){
        this.lastModifiedTime = lastModifiedTime;
    }
    public TravelImage() {
    }
    public Timestamp getLastModifiedTime() {
        return lastModifiedTime;
    }
    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountry_RegionCodeISO() {
        return country_RegionCodeISO;
    }

    public void setCountry_RegionCodeISO(String country_RegionCodeISO) {
        this.country_RegionCodeISO = country_RegionCodeISO;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
