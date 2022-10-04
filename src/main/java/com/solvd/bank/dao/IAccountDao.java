package com.solvd.bank.dao;

import com.solvd.bank.models.AccountModel;

public interface IAccountDao extends IBaseDao<AccountModel> {
    public AccountModel getByUserId(int id);
}
