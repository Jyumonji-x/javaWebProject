package com.project.service;

public interface RegisterService {
    public void createUser(String userName,String email,String pass);
    public boolean hasSameUser(String userName,String email);
}
