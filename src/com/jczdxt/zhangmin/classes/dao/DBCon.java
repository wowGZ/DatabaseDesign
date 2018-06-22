package com.jczdxt.zhangmin.classes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCon{

    private PreparedStatement ps;
    private ResultSet rs;

    public int update(String sql,Object... pras){//参数列表
        int resu=0;//受影响的条数
        try {
            ps=Dao.con.prepareStatement(sql);
            if(pras!=null){
                for(int i=0;i<pras.length;i++){
                    ps.setObject(i+1, pras[i]);
                }
            }
            resu=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resu;
    }

    public ResultSet query(String sql,Object... pras){
        try {
            ps= Dao.con.prepareStatement(sql);
            if(pras!=null){
                for(int i=0;i<pras.length;i++){
                    ps.setObject(i+1, pras[i]);//参数位置，参数值
                }
            }
            rs=ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;//返回结果集
    }
}
