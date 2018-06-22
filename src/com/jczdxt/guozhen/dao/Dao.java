package com.jczdxt.guozhen.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {
    private static String dbName = "jczdxt";
    private static String dbUrl = "jdbc:mysql://localhost:3306/" + dbName
            + "?useUnicode=true&characterEncoding=utf8&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=true";
    private static String dbUser = "root";
    private static String dbPwd = "mknjnhnb,.970817";
    public static Connection con = null;
    static {
        if (con == null) {
            try {
                con = DriverManager.getConnection(dbUrl, dbUser,dbPwd);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private Dao(){}
}
