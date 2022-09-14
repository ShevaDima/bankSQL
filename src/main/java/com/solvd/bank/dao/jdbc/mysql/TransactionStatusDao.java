package com.solvd.bank.dao.jdbc.mysql;


import com.solvd.bank.dao.ITransactionStatusDao;
import com.solvd.bank.models.TransactionStatusModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionStatusDao extends AbstractMySqlDao implements ITransactionStatusDao {
    private final Logger log = LogManager.getLogger(TransactionStatusDao.class);

    @Override
    public void create (TransactionStatusModel obj) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO transaction_statuses(status) VALUES (?)";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, obj.getStatus());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("A new transaction_status was inserted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public TransactionStatusModel getById(int id) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM transaction_statuses WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                TransactionStatusModel m = new TransactionStatusModel();
                m.setId(rs.getInt("id"));
                m.setStatus(rs.getString("status"));
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
        String sql = "DELETE FROM transaction_statuses WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("A transaction_status was deleted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public void update(TransactionStatusModel obj) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE cards SET status=? WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, obj.getStatus());
            ps.setInt(2, obj.getId());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("An existing transaction_status was updated successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

}