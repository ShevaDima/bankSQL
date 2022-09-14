package com.solvd.bank.dao.jdbc.mysql;


import com.solvd.bank.dao.IContactDao;
import com.solvd.bank.models.ContactModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactDao extends AbstractMySqlDao implements IContactDao {
    private final Logger log = LogManager.getLogger(ContactDao.class);

    @Override
    public void create(ContactModel obj) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO contacts(phone1, phone2, email) VALUES (?, ?, ?)";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, obj.getPhone1());
            ps.setString(2, obj.getPhone2());
            ps.setString(3, obj.getEmail());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("A new contact was inserted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public ContactModel getById(int id) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM contacts WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                ContactModel m = new ContactModel();
                m.setId(rs.getInt("id"));
                m.setPhone1(rs.getString("phone1"));
                m.setPhone2(rs.getString("phone2"));
                m.setEmail(rs.getString("email"));
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
        String sql = "DELETE FROM contacts WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("A contact was deleted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public void update(ContactModel obj) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE contacts SET phone1=?, phone2=?, email=? WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, obj.getPhone1());
            ps.setString(2, obj.getPhone2());
            ps.setString(3, obj.getEmail());
            ps.setInt(4, obj.getId());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("An existing contact was updated successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

}