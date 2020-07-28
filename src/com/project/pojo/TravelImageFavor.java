package com.project.pojo;

public class TravelImageFavor {
    private int favorId;
    private int uid;
    private int imageId;
    public TravelImageFavor(int favorId, int uid, int imageId) {
        this.favorId = favorId;
        this.uid = uid;
        this.imageId = imageId;
    }
    public TravelImageFavor() {
    }

    public int getFavorId() {
        return favorId;
    }

    public void setFavorId(int favorId) {
        this.favorId = favorId;
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
}
