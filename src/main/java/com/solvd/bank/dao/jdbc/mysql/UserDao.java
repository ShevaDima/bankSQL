package com.solvd.bank.dao.jdbc.mysql;

import com.solvd.bank.dao.IUserDao;
import com.solvd.bank.models.UserModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends AbstractMySqlDao implements IUserDao {
    private final Logger log = LogManager.getLogger(UserDao.class);

    @Override
    public void create(UserModel obj) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO users(name, surname, contact_id, login_id) VALUES (?, ?, ?, ?)";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, obj.getName());
            ps.setString(2, obj.getSurname());
            ps.setInt(3, obj.getContactId());
            ps.setInt(4, obj.getLoginId());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("A new user was inserted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public UserModel getById(int id) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM users WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                UserModel m = new UserModel();
                m.setId(rs.getInt("id"));
                m.setName(rs.getString("name"));
                m.setSurname(rs.getString("surname"));
                m.setContactId(rs.getInt("contact_id"));
                m.setContactId(rs.getInt("login_id"));
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
        String sql = "DELETE FROM users WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("A user was deleted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public void update(UserModel obj) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE users SET name=?, surname=?, contact_id=?, login_id WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, obj.getName());
            ps.setString(2, obj.getSurname());
            ps.setInt(3, obj.getContactId());
            ps.setInt(4, obj.getLoginId());
            ps.setInt(5, obj.getId());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("An existing user was updated successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public UserModel getByLoginId(int id) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM users WHERE login_id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                UserModel m = new UserModel();
                m.setId(rs.getInt("id"));
                m.setName(rs.getString("name"));
                m.setSurname(rs.getString("surname"));
                m.setContactId(rs.getInt("contact_id"));
                m.setContactId(rs.getInt("login_id"));
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