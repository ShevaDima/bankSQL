package com.solvd.bank.dao.jdbc.mysql;


import com.solvd.bank.connections.AbstractMySqlDao;
import com.solvd.bank.dao.ICardDao;
import com.solvd.bank.models.BankModel;
import com.solvd.bank.models.CardModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardDao extends AbstractMySqlDao implements ICardDao {
    private final Logger log = LogManager.getLogger(CardDao.class);

    @Override
    public void create (CardModel obj) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO cards(type_id, account_id) VALUES (?, ?)";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, obj.getTypeId());
            ps.setInt(2, obj.getAccountId());
            int rows = ps.executeUpdate();

            if (rows > 0) {
//                log.info("A new card was inserted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public CardModel getById(int id) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM cards WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                CardModel m = new CardModel();
                m.setId(rs.getInt("id"));
                m.setTypeId(rs.getInt("type_id"));
                m.setAccountId(rs.getInt("account_id"));
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
        String sql = "DELETE FROM cards WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("A card was deleted successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public void update(CardModel obj) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE cards SET type_id=?, account_id=? WHERE id=?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, obj.getTypeId());
            ps.setInt(2, obj.getAccountId());
            ps.setInt(3, obj.getId());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                log.info("An existing card was updated successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closePreparedStatement(ps);
        closeConnection(c);
    }

    @Override
    public List<CardModel> getAllCards() {
        Connection c = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM cards";
        List<CardModel> list = new ArrayList<>();

        try {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){
                CardModel cardModel = new CardModel();
                cardModel.setId(rs.getInt("id"));
                cardModel.setAccountId(rs.getInt("account_id"));
                cardModel.setTypeId(rs.getInt("type_id"));
                list.add(cardModel);
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