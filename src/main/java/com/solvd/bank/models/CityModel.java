package com.solvd.bank.models;

import java.util.ArrayList;
import java.util.List;

public class CityModel {
    private int id;
    private String city;
    private int countryId;
    private List<AdressModel> adresses = new ArrayList<>();

    public CityModel() {
    }

    public CityModel(int id, String city, int countryId) {
        this.id = id;
        this.city = city;
        this.countryId = countryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public List<AdressModel> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<AdressModel> adresses) {
        this.adresses = adresses;
    }

    @Override
    public String toString() {
        return "com.solvd.bank.models.CityModel{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", countryId=" + countryId +
                ", adresses=" + adresses +
                '}';
    }
}
