package com.solvd.bank.dao.jdbc.mysql;

import com.solvd.bank.dao.IAccountDao;
import com.solvd.bank.models.AccountModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao extends AbstractMySqlDao implements IAccountDao {
    private final Logger log = LogManager.getLogger(AccountDao.class);

    @Override
    public void create(AccountModel obj) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO accounts(user_id, bank_id) VALUES (?, ?)";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, obj.getUserId());
            ps.setInt(2, obj.getBankId());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("A new account was inserted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public AccountModel getById(int id) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM accounts WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()){
                AccountModel m = new AccountModel();
                m.setId(rs.getInt("id"));
                m.setUserId(rs.getInt("user_id"));
                m.setBankId(rs.getInt("bank_id"));
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
        String sql = "DELETE FROM accounts WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("An account was deleted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public void update(AccountModel obj) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE accounts SET user_id=?, bank_id=? WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, obj.getUserId());
            ps.setInt(2, obj.getBankId());
            ps.setInt(3, obj.getId());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("An existing account was updated successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }
}