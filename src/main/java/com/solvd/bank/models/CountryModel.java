package com.solvd.bank.models;

public class CountryModel {
    private int id;
    private String country;

    public CountryModel() {
    }

    public CountryModel(int id, String country) {
        this.id = id;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "com.solvd.bank.models.CountryModel{" +
                "id=" + id +
                ", country='" + country + '\'' +
                '}';
    }
}
