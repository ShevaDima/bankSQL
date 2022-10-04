package com.solvd.bank.dao.jdbc.mysql;

import com.solvd.bank.connections.AbstractMySqlDao;
import com.solvd.bank.dao.IBankDao;
import com.solvd.bank.models.BankModel;
import com.solvd.bank.models.CountryModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankDao extends AbstractMySqlDao implements IBankDao {
    private final Logger log = LogManager.getLogger(BankDao.class);

    @Override
    public void create(BankModel obj) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO banks(name, adress_id) VALUES (?, ?)";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, obj.getName());
            ps.setInt(2, obj.getAdressId());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("A new bank was inserted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public BankModel getById(int id) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM banks WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()){
                BankModel m = new BankModel();
                m.setId(rs.getInt("id"));
                m.setName(rs.getString("name"));
                m.setAdressId(rs.getInt("adress_id"));
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
        String sql = "DELETE FROM banks WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("An bank was deleted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public void update(BankModel obj) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE banks SET name=?, adress_id=? WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, obj.getName());
            ps.setInt(2, obj.getAdressId());
            ps.setInt(3, obj.getId());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("An existing bank was updated successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public List<BankModel> getAllBanks() {
        Connection c = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM banks";
        List<BankModel> list = new ArrayList<>();

        try {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){
                BankModel bankModel = new BankModel();
                bankModel.setId(rs.getInt("id"));
                bankModel.setName(rs.getString("name"));
                bankModel.setAdressId(rs.getInt("adress_id"));
                list.add(bankModel);
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