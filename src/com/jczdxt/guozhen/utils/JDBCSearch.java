package com.jczdxt.guozhen.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCSearch {

    public static void searchByNameOfOneColumn(String sql, String columnName) {
        try {
            Connection connection = JDBCConnectionStart.getConnection("localhost", "design_system", "root", "mknjnhnb,.970817");
            Statement statement = connection.createStatement();

            //查询表中的数据
            try(ResultSet resultSet = statement.executeQuery(sql)){
                while (resultSet.next()) {
                    // 依次打印出查询结果中列名为columName的字符串
                    System.out.println(resultSet.getString(columnName));
                }
            }
            JDBCConnectionStart.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void searchByOrder(String sql ){
        try {
            Connection connection = JDBCConnectionStart.getConnection("localhost", "design_system", "root", "mknjnhnb,.970817");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
//                此处填写你所需要查询的表的属性名称和类型名，并进行输出
//                String studentNumber = resultSet.getString(1);
//                System.out.println(studentNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
