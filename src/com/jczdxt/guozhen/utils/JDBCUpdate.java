package com.jczdxt.guozhen.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.jczdxt.guozhen.utils.JDBCConnectionStart.getConnection;

public class JDBCUpdate {
    public static int update(String tableName, String conditionOne, String newInfo,String conditionTwo){
        int ok = -1;
        try {
            Connection connection = getConnection("localhost", "design_system", "root", "mknjnhnb,.970817");
            Statement statement = connection.createStatement();
            String sql = "update " + tableName + " set " + conditionOne + " = " + newInfo + " where " + conditionTwo;
            ok = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok;
    }
    public static int delete(String tableName, String condition){
        int ok = -1;
        try {
            Connection connection = getConnection("localhost", "design_system", "root", "mknjnhnb,.970817");
            Statement statement = connection.createStatement();
            String sql = "delete " + tableName + " where " + condition;
            ok = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok;
    }
    public static int insert(String tableName, String condition){
        int ok = -1;
        try {
            Connection connection = getConnection("localhost", "design_system", "root", "mknjnhnb,.970817");
            Statement statement = connection.createStatement();
            String sql = "insert into " + tableName + " values " + condition;
            ok = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok;
    }
    public static void showAllInfoOfTable(String tableName){
        try {
            Connection connection = getConnection("localhost", "design_system", "root", "mknjnhnb,.970817");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
            while(resultSet.next()) {
//                此处填写你所需要查询的表的属性名称和类型名，并进行输出
//                String studentNumber = resultSet.getString(1);
//                System.out.println(studentNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String s = "s";
    }
}
