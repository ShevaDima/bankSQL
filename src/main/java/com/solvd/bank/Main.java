package com.solvd.bank;

import com.solvd.bank.dao.jdbc.mysql.CityDao;
import com.solvd.bank.dao.jdbc.mysql.CountryDao;
import com.solvd.bank.models.CityModel;
import com.solvd.bank.models.CountryModel;


public class Main {

    public static void main(String[] args) {

        //Read all countries
        CountryDao countryDao= new CountryDao();
        System.out.println(countryDao.getAllCountries().toString());



        CityDao cd = new CityDao();

        //Create new city
        cd.create(new CityModel(999, "Lviv", 1));

        //Read city
        System.out.println(cd.getById(7));

        //Update city
        cd.update(new CityModel(7, "Washington", 2));

        //Delete city
        cd.remove(7);


    }
}
