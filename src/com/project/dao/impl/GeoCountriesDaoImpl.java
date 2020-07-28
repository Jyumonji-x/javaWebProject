package com.project.dao.impl;

import com.project.dao.DAO;
import com.project.dao.GeoCountriesDao;
import com.project.pojo.GeoCountries;

import java.util.List;

public class GeoCountriesDaoImpl extends DAO<GeoCountries> implements GeoCountriesDao {
    @Override
    public List<GeoCountries> getAll() {
        String sql = "SELECT ISO,Country_RegionName FROM geoCountries_regions ORDER BY Country_RegionName";
        return getForList(sql);
    }

    @Override
    public GeoCountries getCountryByIso(String iso) {
        String sql = "SELECT ISO,Country_RegionName FROM geoCountries_regions WHERE ISO = ?";
        return get(sql,iso);
    }
}
