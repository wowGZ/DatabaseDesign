package com.jczdxt.chenweitao.iframe;

import com.jczdxt.guozhen.dao.Query;

import com.jczdxt.jiangtao.iframe.MainView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class FFQKFrame extends JFrame {
    public static void main(String[] args) {
        new FFQKFrame();
    }
    //发放情况查询界面
    public JPanel mainJPanel;

    private String[] tableName;
    private String[][] content;
    private Vector<String> columnNames;
    private Vector<Vector<String>> records;

    private JPanel topView;
    private JLabel helloTeacherJLabel;
    private JPanel searchViewJPanel;
    private JComboBox<String> searchConditionJComboBox;
    private JTextField inputJTextField;
    private JButton searchButton;

    private JScrollPane tableViewJScrollPane;
    private DefaultTableModel tableModel;
    private JTable showInfoJTable;


    private void initComponent() {
        helloTeacherJLabel = new JLabel("欢迎使用本教材征订与发放系统");

        searchViewJPanel = new JPanel(new FlowLayout());

        searchConditionJComboBox = new JComboBox<>();
        searchConditionJComboBox.addItem(new String("班级"));
        searchConditionJComboBox.addItem(new String("课程"));
        searchConditionJComboBox.addItem(new String("书籍"));
//        searchConditionJComboBox.addItem(new String("发放情况"));
        searchViewJPanel.add(searchConditionJComboBox);

        inputJTextField = new JTextField(15);
        searchViewJPanel.add(inputJTextField);

        searchButton = new JButton("查询");
        searchButton.addActionListener(new SearchButtonListener());
        searchViewJPanel.add(searchButton);

        topView = new JPanel(new BorderLayout());
        topView.add(helloTeacherJLabel, BorderLayout.NORTH);
        topView.add(searchViewJPanel, BorderLayout.CENTER);


    }

    private void initTableData() {
        Query resultContent = new Query();
        resultContent.setSQL("select class.CLno,class.CLnum,course.Cname,book.Bname,teacher_class_course_book.condition " +
                "from class,teacher_class_course_book,book,course,class_course " +
                "where Tno=" + MainView.no + " and class.CLno=teacher_class_course_book.CLno " +
                "and book.Bno=teacher_class_course_book.Bno and course.Cno=teacher_class_course_book.Cno " +
                "and teacher_class_course_book.CLno=class_course.CLno " +
                "and teacher_class_course_book.Cno=class_course.Cno");

        records = resultContent.getRecords();
        columnNames = resultContent.getColumnNames();
        //通过重写方法实现表格内容不可被修改
        tableModel = new DefaultTableModel(records, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        showInfoJTable = new JTable(tableModel);
        showInfoJTable.setRowSorter(new TableRowSorter<>(tableModel));//设置排序
        showInfoJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//设置只可以被单选

//        content = resultContent.getRecord();
//        tableName = resultContent.getColumnName();
//        showInfoJTable = new JTable(content, tableName);

        tableViewJScrollPane = new JScrollPane(showInfoJTable);
    }

    public FFQKFrame() throws HeadlessException {
        initComponent();
        initTableData();
        setTitle("教材征订");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(12, 100, 400, 200);
        setVisible(true);

        setLocationRelativeTo(null);//实现窗体居中

        mainJPanel = new JPanel(new BorderLayout());
        mainJPanel.add(topView, BorderLayout.NORTH);
        mainJPanel.add(tableViewJScrollPane, BorderLayout.CENTER);

        add(mainJPanel);
    }


    private class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (searchConditionJComboBox.getSelectedItem().equals("班级")) {
                String s = inputJTextField.getText().toString();
                Query query = new Query();
                query.setSQL("select class.CLno,CLnum,Cname,Bname,teacher_class_course_book.condition " +
                        "from teacher_class_course_book,class,course,book,class_course " +
                        "where Tno=" + MainView.no + " and teacher_class_course_book.CLno=" + s + " " +
                        "and teacher_class_course_book.CLno=class.CLno " +
                        "and teacher_class_course_book.Cno=course.Cno " +
                        "and teacher_class_course_book.Bno=book.Bno " +
                        "and teacher_class_course_book.CLno=class_course.CLno " +
                        "and teacher_class_course_book.Cno=class_course.Cno");
                records = query.getRecords();
                columnNames = query.getColumnNames();
                tableModel = new DefaultTableModel(records, columnNames){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tableViewJScrollPane.remove(showInfoJTable);
                showInfoJTable = new JTable(tableModel);
                showInfoJTable.setRowSorter(new TableRowSorter<>(tableModel));
                mainJPanel.remove(tableViewJScrollPane);
                tableViewJScrollPane = new JScrollPane(showInfoJTable);
                mainJPanel.add(tableViewJScrollPane, BorderLayout.CENTER);
                validate();

//                content = query.getRecord();
//                tableName = query.getColumnName();
//                showInfoJTable = new JTable(content, tableName);

            } else if (searchConditionJComboBox.getSelectedItem().equals("课程")) {
                String s = inputJTextField.getText().toString();
                Query query = new Query();
                query.setSQL("select class.CLno,CLnum,Cname,Bname,teacher_class_course_book.condition " +
                        "from teacher_class_course_book,class,course,book,class_course " +
                        "where Tno=" + MainView.no + " and course.Cname='" + s + "' " +
                        "and teacher_class_course_book.CLno=class.CLno " +
                        "and teacher_class_course_book.Cno=course.Cno " +
                        "and teacher_class_course_book.Bno=book.Bno " +
                        "and teacher_class_course_book.CLno=class_course.CLno " +
                        "and teacher_class_course_book.Cno=class_course.Cno");
                records = query.getRecords();
                columnNames = query.getColumnNames();
                tableModel = new DefaultTableModel(records, columnNames){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tableViewJScrollPane.remove(showInfoJTable);
                showInfoJTable = new JTable(tableModel);
                showInfoJTable.setRowSorter(new TableRowSorter<>(tableModel));
                mainJPanel.remove(tableViewJScrollPane);
                tableViewJScrollPane = new JScrollPane(showInfoJTable);
                mainJPanel.add(tableViewJScrollPane, BorderLayout.CENTER);
                validate();

            } else if (searchConditionJComboBox.getSelectedItem().equals("书籍")) {
                String s = inputJTextField.getText().toString();
                Query query = new Query();
                query.setSQL("select class.CLno,CLnum,Cname,Bname,teacher_class_course_book.condition " +
                        "from teacher_class_course_book,class,course,book,class_course " +
                        "where Tno=" + MainView.no + " and book.Bname='" + s + "' " +
                        "and teacher_class_course_book.CLno=class.CLno " +
                        "and teacher_class_course_book.Cno=course.Cno " +
                        "and teacher_class_course_book.Bno=book.Bno " +
                        "and teacher_class_course_book.CLno=class_course.CLno " +
                        "and teacher_class_course_book.Cno=class_course.Cno");
                records = query.getRecords();
                columnNames = query.getColumnNames();
                tableModel = new DefaultTableModel(records, columnNames){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tableViewJScrollPane.remove(showInfoJTable);
                showInfoJTable = new JTable(tableModel);
                showInfoJTable.setRowSorter(new TableRowSorter<>(tableModel));
                mainJPanel.remove(tableViewJScrollPane);
                tableViewJScrollPane = new JScrollPane(showInfoJTable);
                mainJPanel.add(tableViewJScrollPane, BorderLayout.CENTER);
                validate();

            }
//            else if (searchConditionJComboBox.getSelectedItem().equals("发放情况")) {
//                String s = inputJTextField.getText().toString();
//                Query query = new Query();
//                query.setSQL("select class.CLno,CLnum,Cname,book.Bname,teacher_class_course_book.condition " +
//                        "from teacher_class_course_book,class,course,class_course " +
//                        "where Tno=" + MainView.no + " and teacher_class_course_book.condition='" + s + "'" +
//                        " and teacher_class_course_book.CLno=class.CLno " +
//                        "and teacher_class_course_book.Cno=course.Cno " +
//                        "and teacher_class_course_book.Bno=book.Bno " +
//                        "and teacher_class_course_book.CLno=class_course.CLno " +
//                        "and teacher_class_course_book.Cno=class_course.Cno");
//                records = query.getRecords();
//                columnNames = query.getColumnNames();
//                tableModel = new DefaultTableModel(records, columnNames){
//                    @Override
//                    public boolean isCellEditable(int row, int column) {
//                        return false;
//                    }
//                };
//                tableViewJScrollPane.remove(showInfoJTable);
//                showInfoJTable = new JTable(tableModel);
//                showInfoJTable.setRowSorter(new TableRowSorter<>(tableModel));
//                mainJPanel.remove(tableViewJScrollPane);
//                tableViewJScrollPane = new JScrollPane(showInfoJTable);
//                mainJPanel.add(tableViewJScrollPane, BorderLayout.CENTER);
//                validate();
//
//            }
        }
    }
}
