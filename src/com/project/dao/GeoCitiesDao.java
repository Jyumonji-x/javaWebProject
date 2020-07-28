package com.project.dao;

import com.project.pojo.GeoCities;

import java.util.List;

public interface GeoCitiesDao {
        public List<GeoCities> getAll();
        public GeoCities getCityById(int uid);
        public List<GeoCities> getCitiesByCountryISO(String iso);
}
