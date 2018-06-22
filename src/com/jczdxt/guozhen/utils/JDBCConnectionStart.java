package com.jczdxt.guozhen.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectionStart {

    public static Connection getConnection(String serverName, String database, String user, String password) throws SQLException {

        String url = "jdbc:mysql://" + serverName + "/" + database
                + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=true";

        return DriverManager.getConnection(url, user, password);
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
