package com.project.pojo;

public class GeoCountries {
    public GeoCountries() {
    }

    public GeoCountries(String iso, String country_regionName) {
        this.iso = iso;
        this.country_regionName = country_regionName;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getCountry_regionName() {
        return country_regionName;
    }
    public void setCountry_regionName(String country_regionName) {
        this.country_regionName = country_regionName;
    }

    private String iso;
    private String country_regionName;
}
