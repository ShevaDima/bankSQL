package com.solvd.bank.dao;

import com.solvd.bank.models.LoginModel;

public interface ILoginDao extends IBaseDao<LoginModel>{
    public LoginModel getByLogin(String login);
}