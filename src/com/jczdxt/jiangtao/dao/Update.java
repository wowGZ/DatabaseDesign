package com.jczdxt.jiangtao.dao;



import com.jczdxt.jiangtao.iframe.MainView;
import com.jczdxt.jiangtao.model.Teacher;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update {
    PreparedStatement preSql;
    public void writeUpdate(Teacher update) {
        String sqlStr = "update Teacher set Tname=?,Tdept=?,Tpassword=?,Tdegree=?,Tphone=? where Tno=?" ;
        int ok = 0;
        try {
            if(!update.getName().equals("")
                    && !update.getDept().equals("")
                    && !update.getPassword().equals("")
                    && !update.getDegree().equals("")
                    && !update.getPhone().equals("")) {
                preSql = Dao.con.prepareStatement(sqlStr);
                preSql.setString(1, update.getName());
                preSql.setString(2, update.getDept());
                preSql.setString(3, update.getPassword());
                preSql.setString(4, update.getDegree());
                preSql.setString(5, update.getPhone());
                preSql.setString(6, MainView.no);
                MainView.name = update.getName();
                MainView.dept = update.getDept();
                MainView.password = update.getPassword();
                MainView.degree = update.getDegree();
                MainView.phone = update.getPhone();
                ok = preSql.executeUpdate();
            }
            else
                ok = -1;
        } catch (SQLException e) {
            update.setSuccess(false);
            JOptionPane.showMessageDialog(null, "发生错误或无此账号", "警告", JOptionPane.WARNING_MESSAGE);
        }
        if (ok != 0&&ok!=-1) {
            update.setSuccess(true);
            JOptionPane.showMessageDialog(null, "修改成功", "恭喜", JOptionPane.WARNING_MESSAGE);
        }
        else if(ok ==-1){
            update.setSuccess(false);
            JOptionPane.showMessageDialog(null, "选项不能为空", "警告", JOptionPane.WARNING_MESSAGE);
        }
    }
}