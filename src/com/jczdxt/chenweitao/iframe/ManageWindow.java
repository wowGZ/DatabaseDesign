package com.jczdxt.chenweitao.iframe;

import com.jczdxt.chenweitao.dao.Distribute;
import com.jczdxt.chenweitao.dao.Query;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageWindow extends JFrame {
    //JFrame的子类


    public ManageWindow() throws HeadlessException {
        String [] tablehead;
        String [][] content;
        JTable table;
        setTitle("教材发放申请：");
        setLayout(new BorderLayout(10, 10));
        JPanel panel1 = new JPanel();

        JPanel panel2 = new JPanel();
        JButton selectAll = new JButton("全部选择");
        JButton clearSelection = new JButton("取消选择");
        JButton distribute = new JButton("发放");

//        panel2.add(selectAll,BorderLayout.WEST);
        panel2.add(clearSelection,BorderLayout.EAST);
        panel2.add(distribute,BorderLayout.EAST);

        Query findRecord = new Query();
        findRecord.setDatabaseName("jczdxt");
//        String str = "未发放";
        findRecord.setSQL("select class.CLno,Cname,Tname,Bname,CLnum,teacher_class_course_book.condition " +
                "from teacher_class_course_book,book,course,teacher,class " +
                "where teacher_class_course_book.Cno=course.Cno " +
                "and teacher_class_course_book.Tno=teacher.Tno " +
                "and teacher_class_course_book.Bno=book.Bno " +
                "and teacher_class_course_book.CLno=class.CLno ");
//        findRecord.setSQL("select class_course.CLno,Cname,Tname,Bname,Clnum,class_course.condition\n" +
//                "from course_book,class_course,teacher_class,course,teacher,book,class\n" +
//                "where course_book.Cno = class_course.Cno and class_course.Clno = teacher_class.CLno and class_course.Cno = course.Cno\n" +
//                "and teacher_class.Tno = teacher.Tno and course_book.Bno = book.Bno and class_course.Clno = class.CLno"+
//                " and class_course.condition ='未发放'"
//        );
        content = findRecord.getRecord();    //返回二维数组（查询的记录）
        tablehead = findRecord.getColumnName();    //返回全部字段（列）名
        for (int i = 0; i < content.length; i++) {
            for (int j = 0; j < content[i].length; j++) {
                System.out.println(content[i][j] + " ");
            }
        }
        DefaultTableModel tableModel = new DefaultTableModel(content, tablehead){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setRowSorter(new TableRowSorter<>(tableModel));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.selectAll();
            }
        });
        clearSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.clearSelection();
            }
        });

        distribute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Distribute(table);

            }
        });

        panel1.add(new JScrollPane(table),BorderLayout.NORTH);


        getContentPane().add(panel1,BorderLayout.CENTER);
        getContentPane().add(panel2,BorderLayout.SOUTH);
        getContentPane().setBackground(Color.white);
        setBounds(60,100,500,350);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {

        ManageWindow window2 = new ManageWindow();

    }
}