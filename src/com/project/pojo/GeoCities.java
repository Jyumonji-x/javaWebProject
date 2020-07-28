package com.project.pojo;

public class GeoCities {
    public GeoCities(int geoNameID, String asciiName, String countryCodeISO, String timeZone) {
        this.geoNameID = geoNameID;
        this.asciiName = asciiName;
        this.countryCodeISO = countryCodeISO;
        this.timeZone = timeZone;
    }

    public GeoCities() {
    }

    public int getGeoNameID() {
        return geoNameID;
    }

    public void setGeoNameID(int geoNameID) {
        this.geoNameID = geoNameID;
    }

    public String getAsciiName() {
        return asciiName;
    }

    public void setAsciiName(String asciiName) {
        this.asciiName = asciiName;
    }

    public String getCountryCodeISO() {
        return countryCodeISO;
    }

    public void setCountryCodeISO(String countryCodeISO) {
        this.countryCodeISO = countryCodeISO;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    private int geoNameID;
    private String asciiName;
    private String countryCodeISO;
    private String timeZone;
}
