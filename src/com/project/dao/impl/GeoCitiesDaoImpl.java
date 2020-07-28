package com.project.dao.impl;

import com.project.dao.DAO;
import com.project.dao.GeoCitiesDao;
import com.project.pojo.GeoCities;
import java.util.List;

public class GeoCitiesDaoImpl extends DAO<GeoCities> implements GeoCitiesDao {
    @Override
    public List<GeoCities> getAll() {
        String sql = "SELECT GeoNameID,AsciiName,Country_RegionCodeISO FROM geoCities ORDER BY AsciiName";
        return getForList(sql);
    }

    @Override
    public GeoCities getCityById(int uid) {
        String sql = "SELECT GeoNameID,AsciiName,Country_RegionCodeISO FROM geoCities WHERE GeoNameID = ?";
        return get(sql,uid);
    }

    @Override
    public List<GeoCities> getCitiesByCountryISO(String iso) {
        String sql = "SELECT GeoNameID,AsciiName,Country_RegionCodeISO FROM geoCities WHERE Country_RegionCodeISO = ? ORDER BY AsciiName";
        return getForList(sql,iso);
    }

}
