package com.jczdxt.zhangmin.classes.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Query {

    private String SQL;
    private String[] columnName;
    private String[][] record;
    private Vector<String> columnNames;//列名
    private Vector<Vector<String>> records;//查询到的记录

    public Query() {

    }

    public void setSQL(String SQL) {
        this.SQL = SQL;
    }

    public String [] getColumnName() {
        return columnName;
    }

    public String [][] getRecord() {
        startQuery();
        return record;
    }

    public Vector<String> getColumnNames() {
        return columnNames;
    }

    public Vector<Vector<String>> getRecords() {
        startQuery();
        return records;
    }

    private void startQuery() {
        Statement sql;
        ResultSet rs;
        try {
            if(!Dao.con.isClosed()) {
                sql = Dao.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs = sql.executeQuery(SQL);
                ResultSetMetaData metaData = rs.getMetaData();

                int columnCount = metaData.getColumnCount();//字段数目
                columnName = new String[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    columnName[i - 1] = metaData.getColumnName(i);
                }
                columnNames = new Vector<>();
                for (int i = 0; i < columnName.length; i++) {
                    columnNames.add(columnName[i]);
                }
                rs.last();

                int recordAmount = rs.getRow();//结果集中的记录数目
                record = new String[recordAmount][columnCount];
                int i = 0;
                rs.beforeFirst();
                while (rs.next()) {
                    for (int j = 1; j <= columnCount; j++) {
                        record[i][j - 1] = rs.getString(j);//第i条记录放入二维数组的第i行
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
