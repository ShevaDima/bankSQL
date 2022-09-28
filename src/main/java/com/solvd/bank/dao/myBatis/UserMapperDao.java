package com.solvd.bank.dao.myBatis;

import com.solvd.bank.dao.IUserDao;
import com.solvd.bank.models.UserModel;
import org.apache.ibatis.session.SqlSession;

public class UserMapperDao implements IUserDao {

    @Override
    public void create(UserModel obj) {
        SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession();
        sqlSession.insert("src.main.resources.myBatis.mappers.UserMapper.create", obj);
        sqlSession.commit();
        sqlSession.close();
        System.out.println("A new user was inserted successfully!");
    }

    @Override
    public UserModel getById(int id) {
        SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession();
        UserModel user = sqlSession.selectOne("src.main.resources.myBatis.mappers.UserMapper.getById", id);
        sqlSession.close();
        return user;
    }

    @Override
    public void remove(int id) {
        SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession();
        sqlSession.delete("src.main.resources.myBatis.mappers.UserMapper.delete", id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void update(UserModel obj) {
        SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession();
        sqlSession.update("src.main.resources.myBatis.mappers.UserMapper.update", obj);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public UserModel getByLoginId(int id) {
        SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession();
        UserModel user = sqlSession.selectOne("src.main.resources.myBatis.mappers.UserMapper.getByLoginId", id);
        sqlSession.close();
        return user;
    }
}
