package com.revature.project1.dao.reimbursementTicket.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import com.revature.project1.dao.reimbursementTicket.IReimbursementDao;
import com.revature.project1.models.ReimbursementTicketModel;
import com.revature.project1.util.JDBCConnectionUtil;

public class ReimbursementDaoSql implements IReimbursementDao {
    Connection conn;
    private static Logger logger = LoggerFactory.getLogger(ReimbursementDaoSql.class);

    public ReimbursementDaoSql() {
        conn = JDBCConnectionUtil.getConnection();
    }

    @Override
    public Optional<ReimbursementTicketModel> findTicketsById(long id) {

        return Optional.empty();
    }

    @Override
    public List<ReimbursementTicketModel> getAll() {

        logger.info("Querying all tickets");

        List<ReimbursementTicketModel> res = new ArrayList<>();

        try (PreparedStatement st = conn.prepareStatement("SELECT * FROM ers_reimbursement;");) {

            ResultSet rs = st.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            logger.info("Query Succesful");
            while (rs.next()) {
                res.add(parseFromSql(rs));

            }

        } catch (SQLException e) {

            logger.warn("Error in Query or parsing %s{}", e.getMessage());
        }

        return res;
    }

    @Override
    public List<ReimbursementTicketModel> getTicketsByAuthorUserName(Integer username) {
        // SELECT * FROM ers_reimbursement WHERE reimb_author=(SELECT eu.ers_users_id
        // from ers_users eu where eu.ers_username = 'abrabarb' )
        logger.info("Querying tickets by user: %s{}", username);
        List<ReimbursementTicketModel> res = new ArrayList<>();

        try (PreparedStatement st = conn.prepareStatement("SELECT * FROM ers_reimbursement WHERE reimb_author = ?;",
                Statement.RETURN_GENERATED_KEYS);) {

            st.setInt(1, username);
            ResultSet rs = st.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            logger.info("Query Succesful");
            while (rs.next()) {

                res.add(parseFromSql(rs));

            }

        } catch (SQLException e) {
            logger.warn("Error in Query or parsing %s{}", e.getMessage());
        }
        return res;
    }

    @Override
    public int save(ReimbursementTicketModel t) {
        int ticketId = 0;
        logger.info("Submitting ticket %s{}", t);
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO public.ers_reimbursement (reimb_amount, reim_submitted,reimb_description,reimb_author,reimb_status_id,reimb_type_id) VALUES (?,?,?,?,?,?);",
                Statement.RETURN_GENERATED_KEYS);) {
            st.setDouble(1, t.getReimbAmount());
            st.setTimestamp(2, Timestamp.valueOf(t.getReimbSubmitted()));
            st.setString(3, t.getReimbDescription());
            st.setInt(5, t.getReimbStausId());
            st.setInt(4, t.getReimbAuthor());
            st.setInt(6, t.getReimbTypeId());

            int rowsDeleted = st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            rs.next();
            ticketId = rs.getInt("reimb_id");
            logger.info("Successful ticket submission");

        } catch (

        SQLException e) {
            System.out.println(e.getMessage());
        }
        return ticketId;

    }

    @Override
    public int updateTicketStatusById(Integer id, Integer statusId, Integer resolver) {
        // TODO Auto-generated method stub
        /*
         * UPDATE public.ers_reimbursement
         * SET reimb_resolved='', reimb_resolver=3, reimb_status_id=2
         * WHERE reimb_id=8;
         */try (PreparedStatement st = conn.prepareStatement(
                "UPDATE public.ers_reimbursement SET reimb_resolved=?, reimb_resolver=?, reimb_status_id=? WHERE reimb_id =? and reimb_status_id=1;")) {
            st.setInt(2, resolver);
            st.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            st.setInt(3, statusId);
            st.setInt(4, id);
            int rowsUpdated = st.executeUpdate();

            return (rowsUpdated == 0) ? (-1) : (rowsUpdated);
        } catch (

        SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }

    }

    private ReimbursementTicketModel parseFromSql(ResultSet rs) throws SQLException {
        ReimbursementTicketModel t = new ReimbursementTicketModel();
        t.setReimbAmount(rs.getDouble("reimb_amount"));
        t.setReimbAuthor(rs.getInt("reimb_author"));
        t.setReimbDescription(rs.getString("reimb_description"));
        t.setReimbId(rs.getInt("reimb_id"));
        t.setReimbSubmitted(rs.getTimestamp("reim_submitted").toLocalDateTime());
        if (rs.getTimestamp("reimb_resolved") != null) {

            t.setReimbResolved(rs.getTimestamp("reimb_resolved").toLocalDateTime());
            t.setReimbResolver(rs.getInt("reimb_resolver"));

        }

        t.setReimbStausId(rs.getInt("reimb_status_id"));
        t.setReimbTypeId(rs.getInt("reimb_type_id"));

        return t;

    }

}
