package com.solvd.bank.dao.myBatis;

import com.solvd.bank.dao.ICountryDao;
import com.solvd.bank.models.CountryModel;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class CountryMapperDao implements ICountryDao {
    @Override
    public void create(CountryModel obj) {
        SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession();
        sqlSession.insert("src.main.resources.myBatis.mappers.CountryMapper.create", obj);
        sqlSession.commit();
        sqlSession.close();
        System.out.println("A new country was inserted successfully!");
    }

    @Override
    public CountryModel getById(int id) {
        SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession();
        CountryModel country = sqlSession.selectOne("src.main.resources.myBatis.mappers.CountryMapper.getById", id);
        sqlSession.close();
        return country;
    }

    @Override
    public void remove(int id) {
        SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession();
        sqlSession.delete("src.main.resources.myBatis.mappers.CountryMapper.delete", id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void update(CountryModel obj) {
        SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession();
        sqlSession.update("src.main.resources.myBatis.mappers.CountryMapper.update", obj);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public List<CountryModel> getAllCountries() {
        List<CountryModel> list = new ArrayList<>();
        SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession();
        list = sqlSession.selectList("src.main.resources.myBatis.mappers.CountryMapper.getAllCountries", list);
        sqlSession.close();
        return list;
    }
}
