package com.solvd.bank.dao;

import com.solvd.bank.models.CardModel;

import java.util.List;

public interface ICardDao extends IBaseDao<CardModel>{
    public List<CardModel> getAllCards();
}
