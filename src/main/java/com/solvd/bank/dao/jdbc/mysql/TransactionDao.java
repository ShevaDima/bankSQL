package com.solvd.bank.dao.jdbc.mysql;


import com.solvd.bank.connections.AbstractMySqlDao;
import com.solvd.bank.dao.ITransactionDao;
import com.solvd.bank.models.BankModel;
import com.solvd.bank.models.TransactionModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao extends AbstractMySqlDao implements ITransactionDao {
    private final Logger log = LogManager.getLogger(TransactionDao.class);

    @Override
    public void create (TransactionModel obj) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO transactions(account_id, date, amount, status_id) VALUES (?, ?, ?, ?)";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, obj.getAccountId());
            ps.setString(2, obj.getDate());
            ps.setString(3, obj.getAmount());
            ps.setInt(4, obj.getStatusId());
            int rows = ps.executeUpdate();

            if (rows > 0) {
//                log.info("A new transaction was inserted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public TransactionModel getById(int id) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM transactions WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                TransactionModel m = new TransactionModel();
                m.setId(rs.getInt("id"));
                m.setAccountId(rs.getInt("account_id"));
                m.setDate(rs.getString("date"));
                m.setAmount(rs.getString("amount"));
                m.setStatusId(rs.getInt("status_id"));
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
        String sql = "DELETE FROM transactions WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("A transaction was deleted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public void update(TransactionModel obj) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE transactions SET account_id=?, date=?, amount=?, status_id=? WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, obj.getAccountId());
            ps.setString(2, obj.getDate());
            ps.setString(3, obj.getAmount());
            ps.setInt(4, obj.getStatusId());
            ps.setInt(5, obj.getId());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("An existing transaction was updated successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public TransactionModel getByAccountId(int id) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM transactions WHERE account_id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                TransactionModel m = new TransactionModel();
                m.setId(rs.getInt("id"));
                m.setAccountId(rs.getInt("account_id"));
                m.setDate(rs.getString("date"));
                m.setAmount(rs.getString("amount"));
                m.setStatusId(rs.getInt("status_id"));
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
    public List<TransactionModel> getAllTransactions() {
        Connection c = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM transactions";
        List<TransactionModel> list = new ArrayList<>();

        try {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){
                TransactionModel transactionModel = new TransactionModel();
                transactionModel.setId(rs.getInt("id"));
                transactionModel.setDate(rs.getString("date"));
                transactionModel.setAmount(rs.getString("amount"));
                transactionModel.setAccountId(rs.getInt("account_id"));
                transactionModel.setStatusId(rs.getInt("status_id"));
                list.add(transactionModel);
            }
            closePreparedStatement(ps);
            closeResultSet(rs);
            closeConnection(c);
            return list;
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