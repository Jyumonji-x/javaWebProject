package com.project.dao;

import com.project.pojo.GeoCountries;
import java.util.List;


public interface GeoCountriesDao {
        public List<GeoCountries> getAll();
        public GeoCountries getCountryByIso(String iso);
}