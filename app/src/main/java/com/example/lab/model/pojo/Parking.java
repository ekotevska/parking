package com.example.lab.model.pojo;

import android.location.Location;

public class Parking {

    String name;
    String city;
    String latitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    String longitude;
    int zafateni;
    int kapacitet;

    public int getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(int kapacitet) {
        this.kapacitet = kapacitet;
    }

    int slobodni;


    public Parking(String name, String city, int zafateni, int slobodni, int kapacitet) {
        this.name = name;
        this.city = city;
        this.zafateni = zafateni;
        this.slobodni = slobodni;
        this.kapacitet = kapacitet;
    }

    public Parking() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZafateni() {
        return zafateni;
    }

    public void setZafateni(int zafateni) {
        this.zafateni = zafateni;
    }

    public int getSlobodni() {
        return slobodni;
    }

    public void setSlobodni(int slobodni) {
        this.slobodni = slobodni;
    }
}
