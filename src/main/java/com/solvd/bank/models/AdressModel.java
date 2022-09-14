package com.solvd.bank.models;

public class AdressModel {
    private int id;
    private String street;
    private String building;
    private int cityId;

    public AdressModel() {
    }

    public AdressModel(int id, String street, String building, int cityId) {
        this.id = id;
        this.street = street;
        this.building = building;
        this.cityId = cityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return "AdressModel{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                ", cityId=" + cityId +
                '}';
    }
}
