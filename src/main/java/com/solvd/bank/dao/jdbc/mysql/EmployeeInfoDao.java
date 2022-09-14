package com.solvd.bank.dao.jdbc.mysql;

import com.solvd.bank.dao.IEmployeeInfoDao;
import com.solvd.bank.models.EmployeeInfoModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeInfoDao extends AbstractMySqlDao implements IEmployeeInfoDao {
    private final Logger log = LogManager.getLogger(EmployeeInfoDao.class);

    @Override
    public void create(EmployeeInfoModel obj) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO employee_info(employee_id, phone, salary) VALUES (?, ?, ?)";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, obj.getEmployeeId());
            ps.setString(2, obj.getPhone());
            ps.setInt(3, obj.getSalary());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("A new employee_info was inserted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public EmployeeInfoModel getById(int id) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM employee_info WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                EmployeeInfoModel m = new EmployeeInfoModel();
                m.setId(rs.getInt("id"));
                m.setEmployeeId(rs.getInt("employee_id"));
                m.setPhone(rs.getString("phone"));
                m.setSalary(rs.getInt("salary"));
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
        String sql = "DELETE FROM employee_info WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("A employee_info was deleted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public void update(EmployeeInfoModel obj) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE employee_info SET employee_id=?, phone=?, salary=? WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, obj.getEmployeeId());
            ps.setString(2, obj.getPhone());
            ps.setInt(3, obj.getSalary());
            ps.setInt(4, obj.getId());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("An existing employee_info was updated successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

}