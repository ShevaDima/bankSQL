package com.solvd.bank.models;

public class ContactModel {
    private int id;
    private String phone1;
    private String phone2;
    private String email;

    public ContactModel() {
    }

    public ContactModel(int id, String phone1, String phone2, String email) {
        this.id = id;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ContactModel{" +
                "id=" + id +
                ", phone1='" + phone1 + '\'' +
                ", phone2='" + phone2 + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
