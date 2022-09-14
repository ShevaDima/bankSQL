package com.solvd.bank.models;

public class CardModel {
    private int id;
    private int accountId;
    private int typeId;

    public CardModel() {
    }

    public CardModel(int id, int accountId, int typeId) {
        this.id = id;
        this.accountId = accountId;
        this.typeId = typeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "CardModel{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", typeId=" + typeId +
                '}';
    }
}
