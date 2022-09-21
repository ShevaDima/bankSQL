package com.solvd.bank.dao;

import com.solvd.bank.models.UserModel;

public interface IUserDao extends IBaseDao<UserModel>{
    public UserModel getByLoginId(int id);
}
