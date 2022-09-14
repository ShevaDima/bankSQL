package com.solvd.bank;

import com.solvd.bank.dao.jdbc.mysql.AbstractMySqlDao;
import com.solvd.bank.dao.jdbc.mysql.CityDao;
import com.solvd.bank.dao.jdbc.mysql.CountryDao;
import com.solvd.bank.models.CityModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {
    private final static Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        //Read all countries
        CountryDao countryDao= new CountryDao();
        log.info(countryDao.getAllCountries().toString());



        CityDao cd = new CityDao();

        //Create new city
        cd.create(new CityModel(999, "Lviv", 1));

        //Read city
        log.info(cd.getById(7));

        //Update city
        cd.update(new CityModel(7, "Washington", 2));

        //Delete city
        cd.remove(7);


    }
}
