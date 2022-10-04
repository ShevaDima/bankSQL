package com.solvd.bank.dao.myBatis;

import com.solvd.bank.connections.MyBatisConnection;
import com.solvd.bank.dao.ICityDao;
import com.solvd.bank.models.CityModel;
import org.apache.ibatis.session.SqlSession;


public class CityMapperDao implements ICityDao {
    @Override
    public void create(CityModel obj) {
        SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession();
        sqlSession.insert("src.main.resources.myBatis.mappers.CityMapper.create", obj);
        sqlSession.commit();
        sqlSession.close();
        System.out.println("A new city was inserted successfully!");
    }

    @Override
    public CityModel getById(int id) {
        SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession();
        CityModel city = sqlSession.selectOne("src.main.resources.myBatis.mappers.CityMapper.getById", id);
        sqlSession.close();
        return city;
    }

    @Override
    public void remove(int id) {
        SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession();
        sqlSession.delete("src.main.resources.myBatis.mappers.CityMapper.delete", id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void update(CityModel obj) {
        SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession();
        sqlSession.update("src.main.resources.myBatis.mappers.CityMapper.update", obj);
        sqlSession.commit();
        sqlSession.close();
    }

}
