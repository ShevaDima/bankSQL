package com.solvd.bank.models;

public class EmployeeModel {
    private int id;
    private String name;
    private String surname;
    private int bankId;
    private int positionId;

    public EmployeeModel() {
    }

    public EmployeeModel(int id, String name, String surname, int bankId, int positionId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.bankId = bankId;
        this.positionId = positionId;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    @Override
    public String toString() {
        return "EmployeeModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", bankId=" + bankId +
                ", positionId=" + positionId +
                '}';
    }
}
