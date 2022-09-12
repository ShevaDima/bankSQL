package com.solvd.bank.dao;

import com.solvd.bank.models.CountryModel;

import java.util.List;

public interface ICountryDao extends IBaseDao<CountryModel> {
    public List<CountryModel> getAllCountries();
}
