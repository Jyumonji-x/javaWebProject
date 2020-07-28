package com.project.service;

public interface LoginService {
    public boolean isUser(String userNameOrEmail,String pass);
    public int getUserUid(String userNameOrEmail,String pass);
}
