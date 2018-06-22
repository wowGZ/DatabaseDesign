package com.jczdxt.chenweitao.iframe;

import com.jczdxt.chenweitao.dao.Query;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class ConditionWindow extends JFrame {
    //JFrame的子类
    public ConditionWindow() throws HeadlessException {
        String [] tablehead;
        String [][] content;
        JTable table;
        this.setTitle("教材发放查询");
        this.setLayout(new BorderLayout(10, 10));
        this.add(new JLabel("发放情况:"), BorderLayout.NORTH);
        Query findRecord = new Query();
        findRecord.setDatabaseName("jczdxt");
        findRecord.setSQL("select class_course.Clno,Cname,Tname,Bname,Clnum,class_course.condition\n" +
                "from course_book,class_course,teacher_class,course,teacher,book,class\n" +
                "where course_book.Cno = class_course.Cno and class_course.Clno = teacher_class.CLno and class_course.Cno = course.Cno\n" +
                "and teacher_class.Tno = teacher.Tno and course_book.Bno = book.Bno and class_course.Clno = class.CLno");
        content = findRecord.getRecord();    //返回二维数组（查询的记录）
        tablehead = findRecord.getColumnName();    //返回全部字段（列）名
        DefaultTableModel tableModel = new DefaultTableModel(content, tablehead){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setRowSorter(new TableRowSorter<>(tableModel));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        table = new JTable(content,tablehead);


        this.add(new JScrollPane(table));
        this.setBackground(Color.white);
        this.setBounds(60,100,940,540);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {

        ConditionWindow window= new ConditionWindow();
    }
}