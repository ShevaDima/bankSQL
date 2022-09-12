package com.solvd.bank.dao.jdbc.mysql;

import com.solvd.bank.dao.ICityDao;
import com.solvd.bank.models.CityModel;
import com.solvd.bank.models.CountryModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityDao extends AbstractMySqlDao implements ICityDao {
    @Override
    public void create(CityModel obj) {
        Connection c = AbstractMySqlDao.getConnection();
        String sql = "INSERT INTO cities(city, country_id) VALUES (?, ?)";

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, obj.getCity());
            ps.setInt(2, obj.getCountryId());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("A new city was inserted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CityModel getById(int id) {
        Connection c = AbstractMySqlDao.getConnection();
        String sql = "SELECT * FROM cities WHERE id=?";

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                CityModel cityModel = new CityModel();
                cityModel.setId(rs.getInt("id"));
                cityModel.setCity(rs.getString("city"));
                cityModel.setCountryId(rs.getInt("country_id"));
                return cityModel;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
        Connection c = AbstractMySqlDao.getConnection();
        String sql = "DELETE FROM cities WHERE id=?";

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("A city was deleted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(CityModel obj) {
        Connection c = AbstractMySqlDao.getConnection();
        String sql = "UPDATE cities SET city=?, country_id=? WHERE id=?";

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, obj.getCity());
            ps.setInt(2, obj.getCountryId());
            ps.setInt(3, obj.getId());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("An existing city was updated successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
