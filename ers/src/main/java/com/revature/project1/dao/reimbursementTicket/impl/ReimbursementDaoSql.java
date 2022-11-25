package com.revature.project1.dao.reimbursementTicket.impl;

import java.security.cert.PKIXRevocationChecker.Option;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.revature.project1.dao.reimbursementTicket.IReimbursementDao;
import com.revature.project1.models.ReimbursementTicketModel;
import com.revature.project1.util.JDBCConnectionUtil;

public class ReimbursementDaoSql implements IReimbursementDao {
    Connection conn;

    public ReimbursementDaoSql() {
        conn = JDBCConnectionUtil.getConnection();
    }

    @Override
    public Optional<ReimbursementTicketModel> findTicketsById(long id) {

        return Optional.empty();
    }

    @Override
    public List<ReimbursementTicketModel> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ReimbursementTicketModel> getTicketsByAuthorUserName(Integer username) {
        List<ReimbursementTicketModel> res = new ArrayList<>();

        try (PreparedStatement st = conn.prepareStatement("SELECT * FROM ers_reimbursement WHERE reimb_author = ?;");) {

            st.setInt(1, username);
            ResultSet rs = st.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {

                /*
                 * public ReimbursementTicketModel(int reimbId, int reimbTypeId, double
                 * reimbAmount, Date reimbSubmitted,
                 * Optional<Date> reimbResolved, String reimbDescription, String reimbReceipt,
                 * String reimbType,
                 * String reimbAuthor, String reimbResolver) {
                 * 
                 */

                res.add(
                        new ReimbursementTicketModel(rs.getInt("reimb_id"),
                                rs.getInt("reimb_type_id"),
                                rs.getDouble("reimb_amount"), LocalDate.now(),
                                Optional.empty(), rs.getString("reimb_description"),
                                rs.getString("reimb_receipt"), rs.getInt("reimb_type_id"),
                                rs.getInt("reimb_author"), rs.getInt("reimb_resolver")));

            }

        } catch (SQLException e) {
        }
        return res;
    }

    @Override
    public int save(ReimbursementTicketModel t) {
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO public.ers_reimbursement (reimb_amount, reim_submitted,reimb_description,reimb_author,reimb_status_id,reimb_type_id) VALUES (?,?,?,?,?,?);")) {
            st.setDouble(1, t.getReimbAmount());
            st.setTimestamp(2, Timestamp.valueOf(t.getReimbSubmitted().atStartOfDay()));
            st.setString(3, t.getReimbDescription());
            st.setInt(5, t.getReimbStausId());
            st.setInt(4, t.getReimbAuthor());
            st.setInt(6, t.getReimbTypeId());

            int rowsDeleted = st.executeUpdate();
            System.out.println(rowsDeleted);
            return 1;

        } catch (

        SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;

    }

    @Override
    public void updateTicketStatusById(Integer id, Integer statusId, Integer resolver) {
        // TODO Auto-generated method stub
        /*
         * UPDATE public.ers_reimbursement
         * SET reimb_resolved='', reimb_resolver=3, reimb_status_id=2
         * WHERE reimb_id=8;
         */try (PreparedStatement st = conn.prepareStatement(
                "UPDATE public.ers_reimbursement SET reimb_resolved=?, reimb_resolver=?, reimb_status_id=?")) {
            st.setInt(2, resolver);
            st.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            st.setInt(3, statusId);
            int rowsDeleted = st.executeUpdate();
            System.out.println(rowsDeleted);

        } catch (

        SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
