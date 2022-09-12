package com.solvd.bank.dao.jdbc.mysql;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class AbstractMySqlDao {
        public static Connection getConnection() {
            Connection c = null;

            try (FileInputStream f = new FileInputStream("src/main/resources/db.properties")) {

                Properties properties = new Properties();
                properties.load(f);

                String url = properties.getProperty("db.url");
                String user = properties.getProperty("db.username");
                String password = properties.getProperty("db.password");

                c = DriverManager.getConnection(url, user, password);

                if (c != null) {
                    System.out.println("Connected");
                }
            } catch (SQLException | IOException e) {
                System.out.println(e.getMessage());
            }
            return c;
        }
}
