package com.jczdxt.chenweitao.dao;

import java.sql.*;


public class Query {
    private String databaseName = "";
    private String SQL;
    private String[] columnName;
    private String[][] record;

    public void setDatabaseName(String s) {
        databaseName = s.trim();
    }

    public void setSQL(String SQL) {
        this.SQL = SQL.trim();
    }

    public String[] getColumnName() {
        if (columnName == null) {
            System.out.println("先查询记录");
            return null;
        }
        return columnName;
    }

    public String[][] getRecord() {
        startQuery();
        return record;
    }

    private void startQuery() {
        Connection con;
        Statement sql;
        ResultSet rs;
        String uri =
                "jdbc:mysql://localhost:3306/" + databaseName +
                        "?useUnicode=true&&characterEncoding=utf8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=true";
        try {
            con = DriverManager.getConnection(uri, "root", "mknjnhnb,.970817");
            sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = sql.executeQuery(SQL);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();    //字段数目
            columnName = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnName[i - 1] = metaData.getColumnName(i);
            }
            rs.last();
            int recordAmount = rs.getRow();    //结果集中的记录数目
            record = new String[recordAmount][columnCount];
            int i = 0;
            rs.beforeFirst();
            while (rs.next()) {
                for (int j = 1; j <= columnCount; j++) {
                    record[i][j - 1] = rs.getString(j);    //第i条记录放入二维数组的第i行
                }
                i++;
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("请输入正确的表名" + e);
        }
    }
}
