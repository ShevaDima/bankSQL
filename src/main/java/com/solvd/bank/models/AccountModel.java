package com.solvd.bank.models;

public class AccountModel {
    private long id;
    private long bankId;
    private long userId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBankId() {
        return bankId;
    }

    public void setBankId(long bankId) {
        this.bankId = bankId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "com.solvd.bank.models.AccountModel{" +
                "id=" + id +
                ", bankId=" + bankId +
                ", userId=" + userId +
                '}';
    }
}
