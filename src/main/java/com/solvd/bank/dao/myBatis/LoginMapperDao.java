package com.solvd.bank.dao.myBatis;

import com.solvd.bank.dao.ILoginDao;
import com.solvd.bank.models.LoginModel;
import org.apache.ibatis.session.SqlSession;

public class LoginMapperDao implements ILoginDao {
    @Override
    public void create(LoginModel obj) {
        SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession();
        sqlSession.insert("src.main.resources.myBatis.mappers.LoginMapper.create", obj);
        sqlSession.commit();
        sqlSession.close();
        System.out.println("A new login was inserted successfully!");
    }

    @Override
    public LoginModel getById(int id) {
        SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession();
        LoginModel login = sqlSession.selectOne("src.main.resources.myBatis.mappers.LoginMapper.getById", id);
        sqlSession.close();
        return login;
    }

    @Override
    public void remove(int id) {
        SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession();
        sqlSession.delete("src.main.resources.myBatis.mappers.LoginMapper.delete", id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void update(LoginModel obj) {
        SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession();
        sqlSession.update("src.main.resources.myBatis.mappers.LoginMapper.update", obj);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public LoginModel getByLogin(String login) {
        SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession();
        LoginModel loginM = sqlSession.selectOne("src.main.resources.myBatis.mappers.LoginMapper.getByLogin", login);
        sqlSession.close();
        return loginM;
    }
}
