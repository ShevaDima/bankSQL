package com.solvd.bank.models;

public class UserModel {
    private int id;
    private String name;
    private String surname;
    private int contactId;

    public UserModel() {
    }

    public UserModel(int id, String name, String surname, int contactId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.contactId = contactId;
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

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", contactId=" + contactId +
                '}';
    }
}
