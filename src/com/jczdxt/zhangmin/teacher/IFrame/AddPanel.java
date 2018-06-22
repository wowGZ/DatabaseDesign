package com.jczdxt.zhangmin.teacher.IFrame;


import com.jczdxt.zhangmin.teacher.Model.User;
import com.jczdxt.zhangmin.teacher.dao.UserDaoImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddPanel extends JPanel {
    private JPanel inputInfoJPanel;
    private JTextField TnoJTextField;
    private JTextField TnameJTextField;
    private JTextField TsexJTextField;
    private JTextField TdeptJTextField;
    private JTextField TdegreeJTextField;
    private JTextField TphoneJTextField;
    private UserDaoImpl userDao;

    public AddPanel() {
        setLayout(new BorderLayout());

        inputInfoJPanel = new JPanel();
        inputInfoJPanel.setLayout(new GridLayout(6,4));

        JLabel label = new JLabel("教师号：");
        label.setBounds(86, 68, 54, 15);
        inputInfoJPanel.add(new JLabel());
        inputInfoJPanel.add(label);
        TnoJTextField = new JTextField();
        TnoJTextField.setBounds(150, 65, 146, 18);
        inputInfoJPanel.add(TnoJTextField);
        inputInfoJPanel.add(new JLabel());
        TnoJTextField.setColumns(10);

        JLabel label1 = new JLabel("姓名：");
        label1.setBounds(86, 114, 54, 15);
        inputInfoJPanel.add(new JLabel());
        inputInfoJPanel.add(label1);
        TnameJTextField = new JTextField();
        TnameJTextField.setBounds(150, 65, 146, 18);
        inputInfoJPanel.add(TnameJTextField);
        inputInfoJPanel.add(new JLabel());
        TnameJTextField.setColumns(10);

        JLabel label2 = new JLabel("性别：");
        label2.setBounds(86, 163, 54, 15);
        inputInfoJPanel.add(new JLabel());
        inputInfoJPanel.add(label2);
        TsexJTextField = new JTextField();
        TsexJTextField.setBounds(150, 160, 146, 23);
        inputInfoJPanel.add(TsexJTextField);
        inputInfoJPanel.add(new JLabel());
        TsexJTextField.setColumns(10);

        JLabel label3 = new JLabel("所在系：");
        label3.setBounds(86, 163, 54, 15);
        inputInfoJPanel.add(new JLabel());
        inputInfoJPanel.add(label3);
        TdeptJTextField = new JTextField();
        TdeptJTextField.setBounds(150, 160, 146, 23);
        inputInfoJPanel.add(TdeptJTextField);
        inputInfoJPanel.add(new JLabel());
        TdeptJTextField.setColumns(10);

        JLabel label4 = new JLabel("学历：");
        label4.setBounds(86, 163, 54, 15);
        inputInfoJPanel.add(new JLabel());
        inputInfoJPanel.add(label4);
        TdegreeJTextField = new JTextField();
        TdegreeJTextField.setBounds(150, 160, 146, 23);
        inputInfoJPanel.add(TdegreeJTextField);
        inputInfoJPanel.add(new JLabel());
        TdegreeJTextField.setColumns(10);

        JLabel label5 = new JLabel("联系电话：");
        label5.setBounds(86, 163, 54, 15);
        inputInfoJPanel.add(new JLabel());
        inputInfoJPanel.add(label5);
        TphoneJTextField = new JTextField();
        TphoneJTextField.setBounds(150, 160, 146, 23);
        inputInfoJPanel.add(TphoneJTextField);
        inputInfoJPanel.add(new JLabel());
        TphoneJTextField.setColumns(10);

        add(inputInfoJPanel, BorderLayout.CENTER);

        JButton btnNewButton = new JButton("添加用户");

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String Tno = TnoJTextField.getText();
                String Tname = TnameJTextField.getText();
                String Tsex = TsexJTextField.getText();
                String Tdept = TdeptJTextField.getText();
                String Tdegree = TdegreeJTextField.getText();
                String Tphone = TphoneJTextField.getText();
                String Tpassword = TnoJTextField.getText();

                User user = new User();
                userDao = new UserDaoImpl();
                user.setTno(Tno);
                user.setTname(Tname);
                user.setTsex(Tsex);
                user.setTdept(Tdept);
                user.setTdegree(Tdegree);
                user.setTphone(Tphone);
                user.setTpassword(Tpassword);


                if (userDao.add(user)) {
                    JOptionPane.showMessageDialog(null, "插入成功");
                    TnoJTextField.setText("");
                    TnameJTextField.setText("");
                    TsexJTextField.setText("");
                    TdeptJTextField.setText("");
                    TdegreeJTextField.setText("");
                    TphoneJTextField.setText("");

                } else {
                    JOptionPane.showMessageDialog(null, "插入失败");
                }

            }
        });
        btnNewButton.setBounds(150, 217, 116, 23);
        JPanel jPanel = new JPanel();
        jPanel.add(btnNewButton);
        add(jPanel,BorderLayout.SOUTH);

    }
}