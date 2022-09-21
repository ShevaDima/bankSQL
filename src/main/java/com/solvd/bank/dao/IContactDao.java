package com.solvd.bank.dao;

import com.solvd.bank.models.ContactModel;

public interface IContactDao extends IBaseDao<ContactModel>{
    public ContactModel getByEmail(String email);
}
