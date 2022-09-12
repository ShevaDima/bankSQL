package com.solvd.bank.dao.jdbc.mysql;

import com.solvd.bank.dao.ICountryDao;
import com.solvd.bank.models.CountryModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDao extends AbstractMySqlDao implements ICountryDao {

    @Override
    public void create(CountryModel obj) {
        Connection c = AbstractMySqlDao.getConnection();
        String sql = "INSERT INTO countries(country) VALUES (?)";

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, obj.getCountry());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("A new country was inserted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CountryModel getById(int id) {
        Connection c = AbstractMySqlDao.getConnection();
        String sql = "SELECT * FROM countries WHERE id=?";

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                CountryModel countryModel = new CountryModel();
                countryModel.setId(rs.getInt("id"));
                countryModel.setCountry(rs.getString("country"));
                return countryModel;
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
        String sql = "DELETE FROM countries WHERE id=?";

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("A country was deleted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(CountryModel obj) {
        Connection c = AbstractMySqlDao.getConnection();
        String sql = "UPDATE countries SET country=? WHERE id=?";

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, obj.getCountry());
            ps.setInt(2, obj.getId());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("An existing country was updated successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CountryModel> getAllCountries() {
        Connection c = AbstractMySqlDao.getConnection();
        String sql = "SELECT * FROM countries";
        List<CountryModel> list = new ArrayList<>();

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                CountryModel countryModel = new CountryModel();
                countryModel.setId(rs.getInt("id"));
                countryModel.setCountry(rs.getString("country"));
                list.add(countryModel);
            }
            return list;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
