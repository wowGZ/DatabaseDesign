package com.jczdxt.guozhen.utils;

public class SQL {

    public static String searchSQL(String attributes, String tableName, String condition){
        String result = "select " + attributes + " from " + tableName + " " + condition;
        return result;
    }

    public static String insertSQL(String tableName, String value){
        String result = "insert into " + tableName + " values " + value;
        return result;
    }

    public static String delete(String tableName, String condition) {
        String result = "delete from " + tableName + " " + condition;
        return result;
    }
}
