package com.solvd.bank.models;

public class UserModel {
    private int id;
    private String name;
    private String surname;
    private int contactId;
    private int loginId;

    public UserModel() {
    }

    public UserModel(int id, String name, String surname, int contactId, int loginId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.contactId = contactId;
        this.loginId = loginId;
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

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    @Override
    public String toString() {
        return "com.solvd.bank.models.UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", contactId=" + contactId +
                ", loginId=" + loginId +
                '}';
    }
}
