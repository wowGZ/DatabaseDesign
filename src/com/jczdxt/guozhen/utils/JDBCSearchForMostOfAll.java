package com.jczdxt.guozhen.utils;

import java.sql.*;

public class JDBCSearchForMostOfAll {
    private String databaseName = "";
    private String SQL;
    private String[] columnName;
    private String[][] record;

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName.trim();
    }

    public void setSQL(String SQL) {
        this.SQL = SQL.trim();
    }

    public String[] getColumnName() {
        return columnName;
    }

    public String[][] getRecord() {
        return record;
    }

    public JDBCSearchForMostOfAll(){

    }

    private void startSearch(){
        try {
            Connection connection = JDBCConnectionStart.getConnection("localhost", "design_system", "root", "mknjnhnb,.970817");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            columnName = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnName[i - 1] = resultSetMetaData.getColumnName(i);
            }
            resultSet.last();
            int recordAmount = resultSet.getRow();
            record = new String[recordAmount][columnCount];
            int i = 0;
            resultSet.beforeFirst();
            while(resultSet.next()){
                for (int j = 1; j <= columnCount; j++) {
                    record[i][j - 1] = resultSet.getString(j);
                }
                i ++;
            }
            JDBCConnectionStart.closeConnection(connection);
        } catch (SQLException e) {
            System.out.println("请输入正确的表名" + e);
        }
    }
}
