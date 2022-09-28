package com.solvd.bank.dao.myBatis;

import com.solvd.bank.dao.IContactDao;
import com.solvd.bank.models.ContactModel;
import org.apache.ibatis.session.SqlSession;

public class ContactMapperDao implements IContactDao {
    @Override
    public void create(ContactModel obj) {
        SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession();
        sqlSession.insert("src.main.resources.myBatis.mappers.ContactMapper.create", obj);
        sqlSession.commit();
        sqlSession.close();
        System.out.println("A new contact was inserted successfully!");
    }

    @Override
    public ContactModel getById(int id) {
        SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession();
        ContactModel contact = sqlSession.selectOne("src.main.resources.myBatis.mappers.ContactMapper.getById", id);
        sqlSession.close();
        return contact;
    }

    @Override
    public void remove(int id) {
        SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession();
        sqlSession.delete("src.main.resources.myBatis.mappers.ContactMapper.delete", id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void update(ContactModel obj) {
        SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession();
        sqlSession.update("src.main.resources.myBatis.mappers.ContactMapper.update", obj);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public ContactModel getByEmail(String email) {
        SqlSession sqlSession = MyBatisConnection.getSqlSessionFactory().openSession();
        ContactModel contact = sqlSession.selectOne("src.main.resources.myBatis.mappers.ContactMapper.getByEmail", email);
        sqlSession.close();
        return contact;
    }
}
