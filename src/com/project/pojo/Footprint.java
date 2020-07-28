package com.project.pojo;
public class Footprint {
    public Footprint(){

    }
    public Footprint(int footId, int uid, int imageId) {
        this.footId = footId;
        this.uid = uid;
        this.imageId = imageId;
    }

    public int getFootId() {
        return footId;
    }

    public void setFootId(int footId) {
        this.footId = footId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    private int footId;
    private int uid;
    private int imageId;
}
