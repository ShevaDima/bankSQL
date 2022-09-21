package com.solvd.bank.dao.jdbc.mysql;


import com.solvd.bank.dao.ILoginDao;
import com.solvd.bank.models.LoginModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao extends AbstractMySqlDao implements ILoginDao {
    private final Logger log = LogManager.getLogger(LoginDao.class);

    @Override
    public void create(LoginModel obj) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO logins(login, password) VALUES (?, ?)";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, obj.getLogin());
            ps.setString(2, obj.getPassword());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("A new login was inserted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public LoginModel getById(int id) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM logins WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                LoginModel m = new LoginModel();
                m.setId(rs.getInt("id"));
                m.setLogin(rs.getString("login"));
                m.setPassword(rs.getString("password"));
                closePreparedStatement(ps);
                closeResultSet(rs);
                closeConnection(c);
                return m;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeResultSet(rs);
        closeConnection(c);
        return null;
    }

    @Override
    public void remove(int id) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM logins WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("A login was deleted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public void update(LoginModel obj) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE logins SET login=?, password=? WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, obj.getLogin());
            ps.setString(2, obj.getPassword());
            ps.setInt(3, obj.getId());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("An existing login was updated successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public LoginModel getByLogin(String login) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM logins WHERE login=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, login);
            rs = ps.executeQuery();

            while (rs.next()) {
                LoginModel m = new LoginModel();
                m.setId(rs.getInt("id"));
                m.setLogin(rs.getString("login"));
                m.setPassword(rs.getString("password"));
                closePreparedStatement(ps);
                closeResultSet(rs);
                closeConnection(c);
                return m;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeResultSet(rs);
        closeConnection(c);
        return null;
    }
}
