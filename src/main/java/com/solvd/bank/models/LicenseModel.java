package com.solvd.bank.models;

public class LicenseModel {
    private int id;
    private int bankId;
    private String number;

    public LicenseModel() {
    }

    public LicenseModel(int id, int bankId, String number) {
        this.id = id;
        this.bankId = bankId;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "LicenseModel{" +
                "id=" + id +
                ", bankId=" + bankId +
                ", number='" + number + '\'' +
                '}';
    }
}
