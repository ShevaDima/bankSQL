package com.solvd.bank.connections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class AbstractMySqlDao {
    private final static Logger log = LogManager.getLogger(AbstractMySqlDao.class);

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
                //log.info("Connected");
            }
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
        return c;
    }

    public static void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
                //log.info("Connection closed");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closePreparedStatement(PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
                //log.info("PreparedStatement closed");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
                //log.info("ResultSet closed");
            }
        }
        catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
