package com.solvd.bank.dao.jdbc.mysql;

import com.solvd.bank.connections.AbstractMySqlDao;
import com.solvd.bank.dao.ICardTypeDao;
import com.solvd.bank.models.BankModel;
import com.solvd.bank.models.CardTypeModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardTypeDao extends AbstractMySqlDao implements ICardTypeDao {
    private final Logger log = LogManager.getLogger(CardTypeDao.class);

    @Override
    public void create (CardTypeModel obj) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO card_types(type) VALUES (?)";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, obj.getType());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("A new card_type was inserted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public CardTypeModel getById(int id) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM card_types WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                CardTypeModel m = new CardTypeModel();
                m.setId(rs.getInt("id"));
                m.setType(rs.getString("type"));
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
        String sql = "DELETE FROM card_types WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("A card_type was deleted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public void update(CardTypeModel obj) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE card_types SET type=? WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, obj.getType());
            ps.setInt(2, obj.getId());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("An existing card_type was updated successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public List<CardTypeModel> getAllCardTypes() {
        Connection c = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM card_types";
        List<CardTypeModel> list = new ArrayList<>();

        try {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){
                CardTypeModel cardTypeModel = new CardTypeModel();
                cardTypeModel.setId(rs.getInt("id"));
                cardTypeModel.setType(rs.getString("type"));
                list.add(cardTypeModel);
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