package com.jczdxt.jiangtao.dao;



import com.jczdxt.jiangtao.model.Teacher;

import java.sql.*;
import javax.swing.*;
public class Insert {
    PreparedStatement preSql;
    public void writeInsert(Teacher insert) {
        String sqlStr = "insert into teacher values(?,?,?,?,?,?,?)";
        int ok = 0;
        try {
            if (!insert.getId().equals("")
                    && !insert.getName().equals("")
                    && !insert.getSex().toString().equals("")
                    && !insert.getDept().equals("")
                    && !insert.getPassword().equals("")
                    && !insert.getDegree().equals("")
                    && !insert.getPhone().equals("")) {
                preSql = Dao.con.prepareStatement(sqlStr);
                preSql.setString(1, insert.getId());
                preSql.setString(2, insert.getName());
                preSql.setString(3, insert.getSex().toString());
                preSql.setString(4, insert.getDept());
                preSql.setString(5, insert.getPassword());
                preSql.setString(6, insert.getDegree());
                preSql.setString(7, insert.getPhone());
                ok = preSql.executeUpdate();
            } else
                ok = -1;
        } catch (SQLException e) {
            insert.setSuccess(false);
            JOptionPane.showMessageDialog(null, "此账号已经注册", "警告", JOptionPane.WARNING_MESSAGE);
        }
        if (ok != 0 && ok != -1) {
            insert.setSuccess(true);
            JOptionPane.showMessageDialog(null, "注册成功", "恭喜", JOptionPane.WARNING_MESSAGE);
        } else if (ok == -1) {
            insert.setSuccess(false);
            JOptionPane.showMessageDialog(null, "选项不能为空，请填写", "警告", JOptionPane.WARNING_MESSAGE);
        }
    }
}
