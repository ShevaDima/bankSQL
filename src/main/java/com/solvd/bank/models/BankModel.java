package com.solvd.bank.models;

import java.util.ArrayList;
import java.util.List;

public class BankModel {
    private int id;
    private String name;
    private int adressId;
    private List<AccountModel> accounts = new ArrayList<>();
    private List<EmployeeModel> employees = new ArrayList<>();

    public BankModel() {
    }

    public BankModel(int id, String name, int adressId) {
        this.id = id;
        this.name = name;
        this.adressId = adressId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAdressId() {
        return adressId;
    }

    public void setAdressId(int adressId) {
        this.adressId = adressId;
    }

    public List<AccountModel> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountModel> accounts) {
        this.accounts = accounts;
    }

    public List<EmployeeModel> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeModel> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "BankModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", adressId=" + adressId +
                ", accounts=" + accounts +
                ", employees=" + employees +
                '}';
    }
}
