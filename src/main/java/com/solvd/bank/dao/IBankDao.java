package com.solvd.bank.dao;

import com.solvd.bank.models.BankModel;
import com.solvd.bank.models.TransactionModel;

import java.util.List;

public interface IBankDao extends IBaseDao<BankModel>{
    public List<BankModel> getAllBanks();
}
