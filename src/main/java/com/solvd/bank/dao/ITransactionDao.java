package com.solvd.bank.dao;

import com.solvd.bank.models.TransactionModel;

import java.util.List;

public interface ITransactionDao extends IBaseDao<TransactionModel>{
    public TransactionModel getByAccountId(int id);
    public List<TransactionModel> getAllTransactions();
}
