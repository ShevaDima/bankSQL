package com.solvd.bank.models;

public class TransactionModel {
    private int id;
    private String date;
    private String amount;
    private int accountId;
    private int statusId;

    public TransactionModel() {
    }

    public TransactionModel(int id, String date, String amount, int accountId, int statusId) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.accountId = accountId;
        this.statusId = statusId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        return "TransactionModel{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                ", accountId=" + accountId +
                ", statusId=" + statusId +
                '}';
    }
}
