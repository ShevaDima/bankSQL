package com.solvd.bank.dao;

public interface IBaseDao<T> {
    void create(T obj);

    T getById(int id);

    void remove(int id);

    void update(T obj);
}
