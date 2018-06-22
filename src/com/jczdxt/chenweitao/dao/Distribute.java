package com.jczdxt.chenweitao.dao;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Distribute {
    JTable table;

    public Distribute(JTable table) {
        if (table.getValueAt(table.getSelectedRow(), 5).equals("已发放")) {
            JFrame frame = new JFrame("提示");
            frame.setLayout(new BorderLayout());
            frame.add(new JLabel("本书已发放"), BorderLayout.CENTER);
            frame.setVisible(true);
            frame.setBounds(0,0,200,100);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        } else {
            this.table = table;
            String cino = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
            Connection con = Dao.getCon();
            try {
                Statement sql = con.createStatement();
                String str = "update class_course set class_course.condition='已发放' where Clno='" + cino + "';";
                if (sql.executeUpdate(str) > 0) {
                    JOptionPane.showMessageDialog(null, "发放成功", "发放情况", JOptionPane.PLAIN_MESSAGE);
                    table.getModel().setValueAt("已发放", table.getSelectedRow(), 5);
                } else {
                    JOptionPane.showMessageDialog(null, "发放失败", "发放情况", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
