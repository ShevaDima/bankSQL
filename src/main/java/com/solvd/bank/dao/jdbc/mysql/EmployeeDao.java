package com.solvd.bank.dao.jdbc.mysql;

import com.solvd.bank.connections.AbstractMySqlDao;
import com.solvd.bank.dao.IEmployeeDao;
import com.solvd.bank.models.EmployeeModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDao extends AbstractMySqlDao implements IEmployeeDao {
    private final Logger log = LogManager.getLogger(EmployeeDao.class);

    @Override
    public void create(EmployeeModel obj) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO employees(name, surname, position_id, bank_id) VALUES (?, ?, ?, ?)";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, obj.getName());
            ps.setString(2, obj.getSurname());
            ps.setInt(3, obj.getPositionId());
            ps.setInt(4, obj.getBankId());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("A new employee was inserted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public EmployeeModel getById(int id) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM employees WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                EmployeeModel m = new EmployeeModel();
                m.setId(rs.getInt("id"));
                m.setName(rs.getString("name"));
                m.setSurname(rs.getString("surname"));
                m.setPositionId(rs.getInt("position_id"));
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
        String sql = "DELETE FROM employees WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("A employee was deleted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public void update(EmployeeModel obj) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE employees SET name=?, surname=?, position_id=?, bank_id=? WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, obj.getName());
            ps.setString(2, obj.getSurname());
            ps.setInt(3, obj.getPositionId());
            ps.setInt(4, obj.getBankId());
            ps.setInt(5, obj.getId());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("An existing employee was updated successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

}
