package com.solvd.bank.models;

import java.util.ArrayList;
import java.util.List;

public class AccountModel {
    private int id;
    private int bankId;
    private int userId;
    private List<TransactionModel> transactions = new ArrayList<>();
    private List<CardModel> cards = new ArrayList<>();

    public AccountModel() {
    }

    public AccountModel(int id, int bankId, int userId) {
        this.id = id;
        this.bankId = bankId;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "com.solvd.bank.models.AccountModel{" +
                "id=" + id +
                ", bankId=" + bankId +
                ", userId=" + userId +
                ", transactions=" + transactions +
                ", cards=" + cards +
                '}';
    }
}
