package com.jczdxt.jiangtao.dao;



import com.jczdxt.jiangtao.model.Teacher;

import javax.swing.*;
import java.sql.PreparedStatement;

import java.sql.SQLException;

public class Refine {
    PreparedStatement preSql;
    public void writeRefine(Teacher refine) {
        String sqlStr = "update Teacher set Tpassword = ? where Tno = ? and Tname = ? " +
                "and Tsex = ? and Tdept = ? and Tdegree = ? and Tphone = ?";
        int ok = 0;
        try {
            if (!refine.getPassword().equals("")) {
                preSql = Dao.con.prepareStatement(sqlStr);
                preSql.setString(1, refine.getPassword());
                preSql.setString(2, refine.getId());
                preSql.setString(3, refine.getName());
                preSql.setString(4, refine.getSex().toString());
                preSql.setString(5, refine.getDept());
                preSql.setString(6, refine.getDegree());
                preSql.setString(7, refine.getPhone());
                ok = preSql.executeUpdate();
            } else
                ok = -1;
        } catch (SQLException e) {
            refine.setSuccess(false);
            JOptionPane.showMessageDialog(null, "信息填写错误", "警告", JOptionPane.WARNING_MESSAGE);
        }
        if (ok != 0 && ok != -1) {
            refine.setSuccess(true);
            JOptionPane.showMessageDialog(null, "修改成功", "恭喜", JOptionPane.WARNING_MESSAGE);
        } else {
            refine.setSuccess(false);
            JOptionPane.showMessageDialog(null, "新密码不能为空", "警告", JOptionPane.WARNING_MESSAGE);
        }
    }
}
