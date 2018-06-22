package com.jczdxt.guozhen.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Query {
//    private String databaseName = "";
    private String SQL;
    private String[] columnName;
    private String[][] record;
    private Vector<String> columnNames;
    private Vector<Vector<String>> records;

    public Vector<String> getColumnNames() {
        return columnNames;
    }

    public Vector<Vector<String>> getRecords() {
        startQuery();
        return records;
    }

    public Query() {

    }
//
//    public void setDatabaseName(String databaseName) {
//        this.databaseName = databaseName;
//    }

    public void setSQL(String SQL) {
        this.SQL = SQL;
    }

    public String[] getColumnName() {
        if (columnName == null){
            System.out.println("先查询记录");
            return null;
        }
        return columnName;
    }

    public String[][] getRecord() {
        startQuery();
        return record;
    }

    public void startQuery() {
//        Connection con;
        Statement sql;
        ResultSet rs;
        try {
//            con = JDBCConnectionStart.getConnection("localhost", "design_system"
////                    , "root", "mknjnhnb,.970817");
            sql = Dao.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = sql.executeQuery(SQL);
            ResultSetMetaData metaData = rs.getMetaData();

            int columnCount = metaData.getColumnCount();
            columnName = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnName[i - 1] = metaData.getColumnName(i);
            }
            columnNames = new Vector<>();
            for (int i = 0; i < columnName.length; i++) {
                columnNames.add(columnName[i]);
            }




            rs.last();
            int recordAmount = rs.getRow();
            record = new String[recordAmount][columnCount];
            int i = 0;
            rs.beforeFirst();
            while (rs.next()) {
                for (int j = 1; j <= columnCount; j++) {
                    record[i][j - 1] = rs.getString(j);
                }
                i++;
            }
            records = new Vector<>();
            for (int row = 0; row < recordAmount; row++) {
                Vector<String> rowV = new Vector<>();
                for (int column = 0; column < columnName.length; column++) {
                    rowV.add(record[row][column]);
                }
                records.add(rowV);
            }
//            Dao.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void startUpdate() {
        Statement sql;
        ResultSet rs;
        try {
            sql = Dao.con.createStatement();
            sql.executeUpdate(SQL);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("failed");
        }
    }
}

