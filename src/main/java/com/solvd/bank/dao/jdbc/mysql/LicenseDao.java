package com.solvd.bank.dao.jdbc.mysql;

import com.solvd.bank.connections.AbstractMySqlDao;
import com.solvd.bank.dao.ILicenseDao;
import com.solvd.bank.models.LicenseModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LicenseDao extends AbstractMySqlDao implements ILicenseDao {
    private final Logger log = LogManager.getLogger(LicenseDao.class);

    @Override
    public void create(LicenseModel obj) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO licenses(number, bank_id) VALUES (?, ?)";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, obj.getNumber());
            ps.setInt(2, obj.getBankId());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("A new license was inserted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public LicenseModel getById(int id) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM licenses WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()){
                LicenseModel m = new LicenseModel();
                m.setId(rs.getInt("id"));
                m.setNumber(rs.getString("number"));
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
        String sql = "DELETE FROM licenses WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("An license was deleted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public void update(LicenseModel obj) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE licenses SET number=?, bank_id=? WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, obj.getNumber());
            ps.setInt(2, obj.getBankId());
            ps.setInt(3, obj.getId());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("An existing license was updated successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }
}