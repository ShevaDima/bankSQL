package com.solvd.bank.models;

public class EmployeeInfoModel {
    private int id;
    private int employeeId;
    private String phone;
    private int salary;

    public EmployeeInfoModel() {
    }

    public EmployeeInfoModel(int id, int employeeId, String phone, int salary) {
        this.id = id;
        this.employeeId = employeeId;
        this.phone = phone;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "EmployeeInfoModel{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", phone='" + phone + '\'' +
                ", salary=" + salary +
                '}';
    }
}
