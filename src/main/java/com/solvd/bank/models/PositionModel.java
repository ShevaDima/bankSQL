package com.solvd.bank.models;

import java.util.ArrayList;
import java.util.List;

public class PositionModel {
    private int id;
    private String position;
    private List<EmployeeModel> employees = new ArrayList<>();

    public PositionModel() {
    }

    public PositionModel(int id, String position) {
        this.id = id;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<EmployeeModel> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeModel> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "PositionModel{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", employees=" + employees +
                '}';
    }
}
