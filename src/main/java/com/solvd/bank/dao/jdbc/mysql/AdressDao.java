package com.solvd.bank.dao.jdbc.mysql;

import com.solvd.bank.connections.AbstractMySqlDao;
import com.solvd.bank.dao.IAdressDao;
import com.solvd.bank.models.AdressModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdressDao extends AbstractMySqlDao implements IAdressDao {
    private final Logger log = LogManager.getLogger(AdressDao.class);

    @Override
    public void create(AdressModel obj) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO adresses(street, building, city_id) VALUES (?, ?, ?)";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, obj.getStreet());
            ps.setString(2, obj.getBuilding());
            ps.setInt(3, obj.getCityId());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("A new adress was inserted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public AdressModel getById(int id) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM adresses WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()){
                AdressModel m = new AdressModel();
                m.setId(rs.getInt("id"));
                m.setStreet(rs.getString("street"));
                m.setBuilding(rs.getString("building"));
                m.setCityId(rs.getInt("city_id"));
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
        String sql = "DELETE FROM adresses WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("An adress was deleted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public void update(AdressModel obj) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE adresses SET street=?, building=?, city_id=? WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, obj.getStreet());
            ps.setString(2, obj.getBuilding());
            ps.setInt(3, obj.getCityId());
            ps.setInt(4, obj.getId());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("An existing adress was updated successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }
}