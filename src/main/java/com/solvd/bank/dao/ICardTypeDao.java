package com.solvd.bank.dao;

import com.solvd.bank.models.CardTypeModel;

import java.util.List;

public interface ICardTypeDao extends IBaseDao<CardTypeModel>{
    public List<CardTypeModel> getAllCardTypes();
}
