package com.example.neptour;

public class Location {
    public String lat;
    public String lon;
    public String image_path;
    public String place_name;
    public String zone;
    public String district;
    public String place_type;

    public Location() {

    }

    public Location(String lat, String lon, String image_path, String place_name, String zone, String district, String place_type) {
        this.lat = lat;
        this.lon = lon;
        this.image_path = image_path;
        this.place_name = place_name;
        this.zone = zone;
        this.district = district;
        this.place_type = place_type;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPlace_type() {
        return place_type;
    }

    public void setPlace_type(String place_type) {
        this.place_type = place_type;
    }
}
