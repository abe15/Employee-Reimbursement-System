package com.revature.project1.dao.user.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.postgresql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.project1.dao.user.IUserDao;
import com.revature.project1.models.UserModel;
import com.revature.project1.util.JDBCConnectionUtil;

public class UserDaoSQL implements IUserDao {

    /*
     * String ers_users_id = "ers_users_id";
     * String ers_username = "ers_username";
     * String ers_password = "ers_password";
     * String user_first_name = "user_first_name";
     * String user_last_name = "user_last_name";
     * String user_email = "user_email";
     * String user_role_id = "ers_users_id";
     */
    private static Logger logger = LoggerFactory.getLogger(UserDaoSQL.class);
    Connection conn;

    public UserDaoSQL() {
        conn = JDBCConnectionUtil.getConnection();
    }

    public UserDaoSQL(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Optional<UserModel> findByUserName(String userName) {
        Optional<UserModel> res;

        try (PreparedStatement st = conn.prepareStatement("SELECT * FROM ers_users WHERE ers_username = ?;");) {

            st.setString(1, userName);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                res = Optional.of(new UserModel(rs.getString("user_first_name"), rs.getString("user_last_name"),
                        rs.getString("ers_username"), rs.getString("user_email"),
                        rs.getString("ers_password")));
                res.get().setUserId(rs.getInt("ers_users_id"));
                res.get().setUserRoleId(rs.getInt("user_role_id"));
                return res;
            }

        } catch (SQLException e) {
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserModel> findByEmail(String email) {
        Optional<UserModel> res;
        String s = "";
        try (Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM ers_users WHERE user_email = " + email);) {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                return (Optional.of(new UserModel(rs.getString(4), rs.getString(5), rs.getString(1), rs.getString(6),
                        rs.getString(3))));

            }

        } catch (SQLException e) {
        }
        return Optional.empty();
    }

    @Override
    public List<UserModel> getAllUsers() {
        List<UserModel> res = new ArrayList<>();
        String s = "";
        try (Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM ers_users");) {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {

                System.out.println(rs.getString(4) + rs.getString(1) + rs.getString(2) + rs.getString(3));
                res.add(new UserModel(rs.getString(4), rs.getString(5), rs.getString(1), rs.getString(6),
                        rs.getString(3)));

            }

        } catch (SQLException e) {
        }
        return res;
    }

    @Override
    public int save(UserModel user) {
        int userId = 0;

        logger.info("UserDaoSQL::save() called. Creating new user...");
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO public.ers_users (ers_username, ers_password,user_first_name,user_last_name, user_email,user_role_id) VALUES (?, ?,?,?,?,?);",
                Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());
            st.setString(3, user.getFirstName());
            st.setString(4, user.getLastName());
            st.setString(5, user.getEmail());
            st.setInt(6, 1);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
            userId = rs.getInt("ers_users_Id");

        } catch (SQLException e) {
            logger.warn("UserDaoSQL::save(). Failed user creation");
            logger.error("UserDaoSQL::save()." + e.getMessage());

        }
        return userId;

    }

}
