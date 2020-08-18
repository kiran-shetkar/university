package com.university.common.connection.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class OracleDBConnection {

    private static final String dbURL = "URL";
    private static final String username = "username";
    private static final String password = "password";

    public static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(dbURL, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static PreparedStatement getPreparedStatement(final Connection con, final String sql){
        try {
            return con.prepareStatement(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void closeConnection(final Connection con){
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
