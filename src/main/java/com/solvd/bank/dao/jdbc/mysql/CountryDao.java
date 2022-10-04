package com.solvd.bank.dao.jdbc.mysql;

import com.solvd.bank.connections.AbstractMySqlDao;
import com.solvd.bank.dao.ICountryDao;
import com.solvd.bank.models.CountryModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDao extends AbstractMySqlDao implements ICountryDao {
    private final Logger log = LogManager.getLogger(CountryDao.class);

    @Override
    public void create(CountryModel obj) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO countries(country) VALUES (?)";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, obj.getCountry());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("A new country was inserted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public CountryModel getById(int id) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM countries WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                CountryModel countryModel = new CountryModel();
                countryModel.setId(rs.getInt("id"));
                countryModel.setCountry(rs.getString("country"));
                closePreparedStatement(ps);
                closeResultSet(rs);
                closeConnection(c);
                return countryModel;
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
        String sql = "DELETE FROM countries WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("A country was deleted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public void update(CountryModel obj) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE countries SET country=? WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, obj.getCountry());
            ps.setInt(2, obj.getId());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("An existing country was updated successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public List<CountryModel> getAllCountries() {
        Connection c = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM countries";
        List<CountryModel> list = new ArrayList<>();

        try {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){
                CountryModel countryModel = new CountryModel();
                countryModel.setId(rs.getInt("id"));
                countryModel.setCountry(rs.getString("country"));
                list.add(countryModel);
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
