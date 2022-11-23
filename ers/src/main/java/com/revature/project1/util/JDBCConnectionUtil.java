package com.revature.project1.util;

import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;

public class JDBCConnectionUtil {

    public static Logger logger = LoggerFactory.getLogger(JDBCConnectionUtil.class);
    private static String url = "jdbc:postgresql://localhost:5436/postgres";
    private static Connection conn;

    // "jdbc:postgresql://[host url]:[posrtnumber]/[database name]"
    public static Connection getConnection() {
        if (conn == null) {
            try {
                logger.info("Making a DB connection with creds: \nURL: %s \nUsername: %s \nPassword: %s",
                        System.getenv("DB_URL"), System.getenv("DB_USERNAME"), System.getenv("DB_PASSWORD"));
                Properties props = new Properties();
                props.setProperty("user", "postgres");
                props.setProperty("password", "pass");

                conn = DriverManager.getConnection(url, props);
            } catch (SQLException e) {
                logger.warn(e.getMessage());
            }
        }
        return conn;

    }
}
