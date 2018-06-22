package com.jczdxt.zhangmin.classes.IFrame;

import com.jczdxt.zhangmin.classes.Model.User;
import com.jczdxt.zhangmin.classes.dao.UserDaoImpl;


import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class QueryPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;// 用于存储表格数据
    private String oldValue = "";// 保存单元格编辑 前的值
    private UserDaoImpl userDao;

    public QueryPanel() {
        setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane, BorderLayout.CENTER);

        table = new JTable();

        scrollPane.setColumnHeaderView(table);
        // 初始化存储表格数据的对象
        model = new DefaultTableModel(new Object[][] {}, new String[] { "班级号", "班级人数", "班级负责人姓名", "负责人电话"});
        // 将数据绑定到对象中
        table.setModel(model);
        table.setRowHeight(30);
        scrollPane.setViewportView(table);//JScrollPane 添加JTextarea

        userDao = new UserDaoImpl();
        loadData();
        // 为表格绑定修改值后的事件
        model.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getColumn() < 0)
                    return;
                String nVal = table.getValueAt(e.getLastRow(), e.getColumn())
                        .toString();
                // 如果旧的值 和新的值一样，直接 返回
                if (nVal.equals(oldValue)) {
                    return;
                }
                // 判断当前编辑的单元格是否是主键列
                if (e.getColumn() == 0) {
                    // 还原旧的值
                    table.setValueAt(oldValue, e.getLastRow(), e.getColumn());
                    return;
                }
                // 更新数据
                User user = new User();
                user.setCLno(table.getValueAt(e.getLastRow(), 0).toString());
                user.setCLnum(Integer.valueOf(table.getValueAt(e.getLastRow(), 1).toString()));
                user.setCLFname(table.getValueAt(e.getLastRow(), 2).toString());
                user.setCLFphone(table.getValueAt(e.getLastRow(), 3).toString());

                String s = "where CLno =" + table.getValueAt(e.getLastRow(), 0).toString();

                userDao.update(user,s);

                loadData();
            }
        });
    }

    public void loadData() {
        // 清除旧的数据
        model.getDataVector().clear();
        // 查询所有的用户信息
        List<User> list = userDao.queryAll();
        // 遍历每一条数据，添加到model中
        int i = 0;
        for (User user : list) {
            // 添加 行数据
            model.addRow(new Object[] { user.getCLno(),user.getCLnum(),user.getCLFname(),user.getCLFphone() });
        }
    }

    public void del() {
        if (table.getSelectedRowCount() <= 0) {
            JOptionPane.showMessageDialog(null, "请选择要删除的数据行");
            return;
        }
        int result = JOptionPane.showConfirmDialog(null, "是否确定要删除");
        // 判断用户是否点击
        if (result == JOptionPane.OK_OPTION) {
            String Tno = table.getValueAt(
                    table.getSelectedRow(), 0).toString();
            userDao.delete(Tno);
            loadData();
        }
    }

}