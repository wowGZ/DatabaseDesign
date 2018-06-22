package com.jczdxt.zhangmin.teacher.IFrame;

import com.jczdxt.zhangmin.teacher.dao.Query;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class MainFrame extends JFrame implements ActionListener{

    JPanel contentPanel;
    JPanel centerpanel;
    JPanel searchPanel;
    JPanel buttonPanel;
    private JTextField searchJTexField;
    private JButton btnDel;
    private JButton btnAdd;
    private JButton btnLoadData;
    private JButton btnSearch;
    private JTable table;
    private JScrollPane jsp;
    private String [] tableHead;
    private String [][] content;
    private Vector<String> titles;
    private Vector<Vector<String>> records;
    private AddPanel add;
    private QueryPanel query;

    public MainFrame() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 546, 383);
        contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPanel);

        buttonPanel = new JPanel();
        contentPanel.add(buttonPanel, BorderLayout.NORTH);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        centerpanel = new JPanel();
        centerpanel.setBackground(Color.LIGHT_GRAY);
        contentPanel.add(centerpanel, BorderLayout.CENTER);
        centerpanel.setLayout(new BorderLayout(0,0));

        searchPanel = new JPanel();
        searchJTexField = new JTextField(10);
        btnSearch = new JButton("查询");
        btnSearch.setActionCommand("search");
        buttonPanel.add(btnSearch);

        searchPanel.add(searchJTexField,BorderLayout.WEST);
        searchPanel.add(btnSearch,BorderLayout.EAST);
        contentPanel.add(searchPanel,BorderLayout.SOUTH);
        searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));

        btnLoadData = new JButton("获取数据");
        btnLoadData.setActionCommand("loaddata");
        buttonPanel.add(btnLoadData);

        btnDel = new JButton("删除所选");
        buttonPanel.add(btnDel);
        btnDel.setActionCommand("del");

        btnAdd = new JButton("添加用户");
        buttonPanel.add(btnAdd);
        btnAdd.setActionCommand("add");

        btnDel.addActionListener(this);
        btnLoadData.addActionListener(this);
        btnAdd.addActionListener(this);
        btnSearch.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command=e.getActionCommand();//触发按钮
        System.out.println(command);
        if(command.equals("search")) {
            centerpanel.removeAll();//清除
            Query query = new Query();
            query.setSQL("select * from teacher where Tno =" + searchJTexField.getText());
            records = query.getRecords();
            titles = query.getColumnNames();
            DefaultTableModel tableModel = new DefaultTableModel(records,titles);
            table = new JTable(tableModel);
            table.setRowSorter(new TableRowSorter<>(tableModel));
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//            content = query.getRecord();
//            tableHead = query.getColumnName();
//            table = new JTable(content,tableHead);
            jsp = new JScrollPane(table);
            centerpanel.add(jsp, BorderLayout.CENTER);
           // query.getTable("select * from teacher where Tno = ?");
            centerpanel.updateUI();//刷新面板
        } else
          if(command.equals("add")){
            centerpanel.removeAll();
            add=new AddPanel();
            centerpanel.add(add,BorderLayout.CENTER);
            centerpanel.updateUI();
        }else if(command.equals("loaddata")){
            centerpanel.removeAll();
            query=new QueryPanel();
            centerpanel.add(query,BorderLayout.CENTER);
            centerpanel.updateUI();
        }else if(command.equals("del")){
            if(query==null){
                JOptionPane.showMessageDialog(null, "没有数据");
                return;
            }
            query.del();
        }

    }
//    public static void main(String[] args) {
//        MainFrame frame = new MainFrame();
//        frame.setVisible(true);
////        JFrame frame = new JFrame();
////        frame.getContentPane().add(new MainFrame().getContentPane());
////        frame.setVisible(true);
//    }
}