package com.solvd.bank.dao.jdbc.mysql;

import com.solvd.bank.dao.ICityDao;
import com.solvd.bank.models.CityModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityDao extends AbstractMySqlDao implements ICityDao {
    private final Logger log = LogManager.getLogger(CityDao.class);

    @Override
    public void create(CityModel obj) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO cities(city, country_id) VALUES (?, ?)";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, obj.getCity());
            ps.setInt(2, obj.getCountryId());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("A new city was inserted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public CityModel getById(int id) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM cities WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()){
                CityModel cityModel = new CityModel();
                cityModel.setId(rs.getInt("id"));
                cityModel.setCity(rs.getString("city"));
                cityModel.setCountryId(rs.getInt("country_id"));
                closePreparedStatement(ps);
                closeResultSet(rs);
                closeConnection(c);
                return cityModel;
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
        String sql = "DELETE FROM cities WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("A city was deleted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public void update(CityModel obj) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE cities SET city=?, country_id=? WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, obj.getCity());
            ps.setInt(2, obj.getCountryId());
            ps.setInt(3, obj.getId());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("An existing city was updated successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }
}
