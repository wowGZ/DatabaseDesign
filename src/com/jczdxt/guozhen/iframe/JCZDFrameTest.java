package com.jczdxt.guozhen.iframe;

import com.jczdxt.guozhen.dao.Query;
import com.jczdxt.guozhen.utils.FrameToCenter;

import javax.swing.*;
import java.awt.*;

public class JCZDFrameTest extends JFrame {
    private String[] tableName;
    private String[][] content;
    private JScrollPane tableJScrollPane;
    JTable showInfoJTable;
    JPanel jPanel;
    JPanel jPanel2;
    JTextArea jTextArea;
    JTextArea jTextArea2;
    JTable jTable;

    public JCZDFrameTest() throws HeadlessException {
        init();
        this.add(tableJScrollPane);
        this.setBounds(12, 100, 400, 200);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        FrameToCenter frameToCenter = new FrameToCenter(this);
    }

//    private void connection(){
//        try {
//            Connection connection = JDBCConnectionStart.getConnection("localhost", "design_system"
//                    , "root", "mknjnhnb,.970817");
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM Student");
//            String s = new String();
//            while (resultSet.next()){
//                s = s.concat(resultSet.getString(1));
//            }
//            jTextArea.setText(s);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    private void init(){
        Query findRecord = new Query();
//        findRecord.setDatabaseName("jczdxt");
        findRecord.setSQL("select * from teacher");
        content = findRecord.getRecord();
        tableName = findRecord.getColumnName();
        showInfoJTable = new JTable(content, tableName);
        tableJScrollPane = new JScrollPane(showInfoJTable);
    }

}
