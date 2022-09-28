package com.solvd.bank.dao.myBatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisConnection {
    private static InputStream inputStream;

    static {
        try {
            String resource = "mybatis-config.xml";
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
