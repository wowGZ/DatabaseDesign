package com.jczdxt.chenweitao.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {


    public static Connection getCon( )
    {
        String dbName = "jczdxt";
        String dbUrl = "jdbc:mysql://localhost:3306/" + dbName
                + "?useUnicode=true&characterEncoding=utf8&useJDBCCompliantTimezoneShift=true" +
                "&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=true";
        String dbUser = "root";
        String dbPwd = "mknjnhnb,.970817";
        Connection con=null;
        try {
            con = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  con;
    }




}
