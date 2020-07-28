package com.project.pojo;

import java.util.Date;

public class TravelUser {
    private int uid;
    private String userName;
    private Date dateJoined;
    private Date dateLastModified;
    private String email;
    private String pass;
    private int state;
    private boolean permission;
    public TravelUser() {
    }
    public boolean isPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }
    public TravelUser(int uid, String userName, String pass, int state, Date dateJoined, Date dateLastModified, String email) {
        this.uid = uid;
        this.userName = userName;
        this.pass = pass;
        this.state = state;
        this.dateJoined = dateJoined;
        this.dateLastModified = dateLastModified;
        this.email = email;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Date getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(Date dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
