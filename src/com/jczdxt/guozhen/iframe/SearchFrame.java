package com.jczdxt.guozhen.iframe;

import com.jczdxt.chenweitao.iframe.FFQKFrame;
import com.jczdxt.guozhen.dao.Query;
import com.jczdxt.jiangtao.iframe.MainView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class SearchFrame extends JFrame{
    public static void main(String[] args) {
        new SearchFrame();
    }
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
        searchConditionJComboBox.addItem(new String("书籍编号"));
        searchConditionJComboBox.addItem(new String("书籍名"));
        searchConditionJComboBox.addItem(new String("作者"));
        searchConditionJComboBox.addItem(new String("出版社"));
        searchViewJPanel.add(searchConditionJComboBox);

        inputJTextField = new JTextField(15);
        searchViewJPanel.add(inputJTextField);

        searchButton = new JButton("查询");
        searchButton.addActionListener(new SearchFrame.SearchButtonListener());
        searchViewJPanel.add(searchButton);

        JButton showAllJbutton = new JButton("显示全部");
        showAllJbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Query query = new Query();
                query.setSQL("select * from book");
                content = query.getRecord();
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
        });
        searchViewJPanel.add(showAllJbutton);

        topView = new JPanel(new BorderLayout());
        topView.add(helloTeacherJLabel, BorderLayout.NORTH);
        topView.add(searchViewJPanel, BorderLayout.CENTER);


    }

    private void initTableData() {
        Query resultContent = new Query();
        resultContent.setSQL("select * from book");

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


    private class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (searchConditionJComboBox.getSelectedItem().equals("书籍编号")) {
                String s = inputJTextField.getText().toString();
                Query query = new Query();
                query.setSQL("select * from book where Bno=" + s);
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


            } else if (searchConditionJComboBox.getSelectedItem().equals("书籍名")) {
                String s = inputJTextField.getText().toString();
                Query query = new Query();
                query.setSQL("select * from book where Bname='" + s + "'");
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

            } else if (searchConditionJComboBox.getSelectedItem().equals("作者")) {
                String s = inputJTextField.getText().toString();
                Query query = new Query();
                query.setSQL("select * from book where author='"+ s + "'");
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

            } else if (searchConditionJComboBox.getSelectedItem().equals("出版社")){
                String s = inputJTextField.getText().toString();
                Query query = new Query();
                query.setSQL("select * from book where publish='" + s + "'");
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
        }
    }
    public SearchFrame() throws HeadlessException {
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
}
