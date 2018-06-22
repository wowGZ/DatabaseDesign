package com.jczdxt.zhangmin.classes.IFrame;




import com.jczdxt.zhangmin.classes.Model.User;
import com.jczdxt.zhangmin.classes.dao.UserDaoImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddPanel extends JPanel {
    private JPanel inputInfoJPanel;
    private JTextField CLnoJTextField;
    private JTextField CLnumJTextField;
    private JTextField CLFnameJTextField;
    private JTextField CLFphoneJTextField;
    private UserDaoImpl userDao;

    public AddPanel() {
        setLayout(new BorderLayout());

        inputInfoJPanel = new JPanel();
        inputInfoJPanel.setLayout(new GridLayout(4,4));

        JLabel label = new JLabel("班级号：");
        label.setBounds(86, 68, 54, 15);
        inputInfoJPanel.add(new JLabel());
        inputInfoJPanel.add(label);
        CLnoJTextField = new JTextField();
        CLnoJTextField.setBounds(150, 65, 146, 18);
        inputInfoJPanel.add(CLnoJTextField);
        inputInfoJPanel.add(new JLabel());
        CLnoJTextField.setColumns(10);

        JLabel label1 = new JLabel("班级人数：");
        label1.setBounds(86, 114, 54, 15);
        inputInfoJPanel.add(new JLabel());
        inputInfoJPanel.add(label1);
        CLnumJTextField = new JTextField();
        CLnumJTextField.setBounds(150, 65, 146, 18);
        inputInfoJPanel.add(CLnumJTextField);
        inputInfoJPanel.add(new JLabel());
        CLnumJTextField.setColumns(10);

        JLabel label2 = new JLabel("班级负责人姓名：");
        label2.setBounds(86, 163, 54, 15);
        inputInfoJPanel.add(new JLabel());
        inputInfoJPanel.add(label2);
        CLFnameJTextField = new JTextField();
        CLFnameJTextField.setBounds(150, 160, 146, 23);
        inputInfoJPanel.add(CLFnameJTextField);
        inputInfoJPanel.add(new JLabel());
        CLFnameJTextField.setColumns(10);

        JLabel label3 = new JLabel("负责人电话：");
        label3.setBounds(86, 163, 54, 15);
        inputInfoJPanel.add(new JLabel());
        inputInfoJPanel.add(label3);
        CLFphoneJTextField = new JTextField();
        CLFphoneJTextField.setBounds(150, 160, 146, 23);
        inputInfoJPanel.add(CLFphoneJTextField);
        inputInfoJPanel.add(new JLabel());
        CLFphoneJTextField.setColumns(10);

        add(inputInfoJPanel, BorderLayout.CENTER);

        JButton btnNewButton = new JButton("添加班级");

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String CLno=CLnoJTextField.getText();
                String CLnum=CLnumJTextField.getText();
                String CLFname = CLFnameJTextField.getText();
                String CLFphone = CLFphoneJTextField.getText();

                User user=new User();
                userDao = new UserDaoImpl();
                user.setCLno(CLno);
                user.setCLnum(Integer.valueOf(CLnum));
                user.setCLFname(CLFname);
                user.setCLFphone(CLFphone);


                if(userDao.add(user)){
                    JOptionPane.showMessageDialog(null, "插入成功");
                    CLnoJTextField.setText("");
                    CLnumJTextField.setText("");
                    CLFnameJTextField.setText("");
                    CLFphoneJTextField.setText("");


                }else{
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