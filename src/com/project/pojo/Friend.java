package com.project.pojo;

public class Friend {
    public Friend(int uid1, int uid2) {
        this.uid1 = uid1;
        this.uid2 = uid2;
    }

    private int uid1;

    public int getUid1() {
        return uid1;
    }

    public void setUid1(int uid1) {
        this.uid1 = uid1;
    }

    public int getUid2() {
        return uid2;
    }

    public void setUid2(int uid2) {
        this.uid2 = uid2;
    }

    private int uid2;
    public Friend(){}

}
