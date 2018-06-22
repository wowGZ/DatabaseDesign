package com.jczdxt.jiangtao.dao;



import com.jczdxt.jiangtao.model.Teacher;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {
    PreparedStatement preSql;
    public void writeDelete(Teacher delete) {
        String sqlStr = "delete from Teacher where Tno =? and Tpassword = ?";
        int ok = 0;
        try {if(!delete.getPassword().equals("")) {
            preSql = Dao.con.prepareStatement(sqlStr);
            preSql.setString(1, delete.getId());
            preSql.setString(2, delete.getPassword());
            ok = preSql.executeUpdate();
        }
        else
            ok = -1;
        } catch (SQLException e) {
            delete.setSuccess(false);
            JOptionPane.showMessageDialog(null, "无此账号或信息填写错误", "警告", JOptionPane.WARNING_MESSAGE); }
        if (ok != 0 && ok != -1) {
            delete.setSuccess(true);
            JOptionPane.showMessageDialog(null, "注销成功", "恭喜", JOptionPane.WARNING_MESSAGE);
        }
        else if (ok==-1){
            delete.setSuccess(false);
            JOptionPane.showMessageDialog(null, "密码不能为空", "警告", JOptionPane.WARNING_MESSAGE);
        }
    }
}
