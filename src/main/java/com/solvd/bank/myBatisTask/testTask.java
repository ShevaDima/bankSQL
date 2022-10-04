package com.solvd.bank.myBatisTask;

import com.solvd.bank.dao.myBatis.CountryMapperDao;
import com.solvd.bank.dao.myBatis.UserMapperDao;
import com.solvd.bank.models.UserModel;

public class testTask {
    public void testQuery() {
        UserMapperDao userMapperDAO = new UserMapperDao();

        //Create
        userMapperDAO.create(new UserModel(123, "qq", "qq", 1, 1));

        //Read
        UserModel user = userMapperDAO.getById(3);
        System.out.println(user.toString());

        //Update
        userMapperDAO.update(new UserModel(3, "xx", "qq", 1, 1));

        //Delete
        userMapperDAO.remove(1);

        //ReadByLogin
        UserModel userByLogin = userMapperDAO.getByLoginId(5);
        System.out.println(userByLogin.toString());

        CountryMapperDao countryMapperDao = new CountryMapperDao();
        System.out.println(countryMapperDao.getAllCountries());
    }
}
