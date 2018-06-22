package com.jczdxt.jiangtao.dao;


import com.jczdxt.jiangtao.iframe.MainView;
import com.jczdxt.jiangtao.model.User;

import javax.swing.*;
import java.sql.*;
public class Login {
    PreparedStatement preSql;
    ResultSet rs;
    public User loading(User login) {
    String id = login.getId();
    String password = login.getPassword();
    String sqlStr1 = "select Ano,Apassword from Admin where Ano = ? and Apassword = ?";
    String sqlStr2 = "select Tno,Tname,Tsex,Tdept,Tpassword,Tdegree,Tphone from Teacher where Tno = ? and Tpassword = ?";
    try{
        if(login.getIdentity())
            preSql = Dao.con.prepareStatement(sqlStr1);
        else
            preSql = Dao.con.prepareStatement(sqlStr2);
        preSql.setString(1, id);
        preSql.setString(2, password);
        rs = preSql.executeQuery();
        if(rs.next()==true){
            if(!login.getIdentity()) {
                MainView.no = rs.getString("Tno");
                MainView.name = rs.getString("Tname");
                MainView.sex = rs.getString("Tsex");
                MainView.dept = rs.getString("Tdept");
                MainView.password = rs.getString("Tpassword");
                MainView.degree = rs.getString("Tdegree");
                MainView.phone = rs.getString("Tphone");
            }
            login.setSuccess(true);
            JOptionPane.showMessageDialog(null, "登陆成功", "恭喜", JOptionPane.WARNING_MESSAGE);
        }
        else{
            login.setSuccess(false);
            JOptionPane.showMessageDialog(null,"登录失败,请重新尝试","登录失败",JOptionPane.WARNING_MESSAGE);
        }
        }
        catch (SQLException e){}
        return login;
    }
}
