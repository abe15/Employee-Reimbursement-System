package com.revature.project1.service.user.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.postgresql.*;
import com.revature.project1.models.UserModel;
import com.revature.project1.service.user.IUserDao;

public class UserDaoSQL implements IUserDao {

    String url = "jdbc:postgresql://localhost:5436/postgres";

    String ers_users_id = "ers_users_id";
    String ers_username = "ers_username";
    String ers_password = "ers_password";
    String user_first_name = "user_first_name";
    String user_last_name = "user_last_name";
    String user_email = "user_email";
    String user_role_id = "ers_users_id";

    Connection conn;

    public UserDaoSQL() {
        try {
            Properties props = new Properties();
            props.setProperty("user", "postgres");
            props.setProperty("password", "pass");

            conn = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
        }

    }

    @Override
    public Optional<UserModel> findByUserName(String userName) {
        Optional<UserModel> res;
        String s = "";
        try (Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM ers_users WHERE ers_username = " + userName);) {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {

                return Optional.of(new UserModel(rs.getString(3), rs.getString(4), rs.getString(1), rs.getString(5),
                        rs.getString(2)));

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
    public List<Optional<UserModel>> getAllUsers() {
        List<Optional<UserModel>> res = new ArrayList<>();
        String s = "";
        try (Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM ers_users");) {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {

                System.out.println(rs.getString(4) + rs.getString(1) + rs.getString(2) + rs.getString(3));
                res.add(Optional.of(new UserModel(rs.getString(4), rs.getString(5), rs.getString(1), rs.getString(6),
                        rs.getString(3))));

            }

        } catch (SQLException e) {
        }
        return res;
    }

    @Override
    public void save(UserModel user) {

        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO public.ers_users (ers_username, ers_password,user_first_name,user_last_name, user_email,user_role_id) VALUES (?, ?,?,?,?,1);")) {
            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());
            st.setString(3, user.getFirstName());
            st.setString(4, user.getLastName());
            st.setString(5, user.getEmail());

            int rowsDeleted = st.executeUpdate();

            System.out.println(rowsDeleted);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
