package com.jczdxt.jiangtao.iframe;

import com.jczdxt.jiangtao.dao.Refine;
import com.jczdxt.jiangtao.model.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefineView extends JFrame implements ActionListener {
        private Teacher teacher;
        private JTextField no;//账号文本框
        private JTextField name;//姓名文本框
        private JComboBox<String> sex;//性别
        private JTextField dept;//系别文本框
        private JPasswordField password;//密码文本框
        private JTextField degree;//职称文本框
        private JTextField phone;//电话文本框
        private JLabel noLabel;//账号标签
        private JLabel nameLabel;//姓名标签
        private JLabel sexLabel;//性别标签
        private JLabel deptLabel;//系别标签
        private JLabel passwordLabel;//密码标签
        private JLabel degreeLabel;//职称标签
        private JLabel phoneLabel;//电话标签
        private JButton insertButton;//注册账号

        RefineView() {
            teacher = new Teacher();
            this.setTitle("找回密码");
            init();
            this.setBounds(0, 0, 300, 300);//大小及位置
            this.setLayout(null);
            this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);//关闭方式
            this.setLocationRelativeTo(null);//居中
            this.setVisible(true);//显示
            this.setResizable(false);//不可变换大小
            insertButton.addActionListener(this);
        }

        public void init() {
            Font font = new Font("宋体", Font.PLAIN, 12);

            no = new JTextField();//账号文本框
            no.setBounds(75, 30, 200, 20);

            name = new JTextField();//姓名文本框
            name.setBounds(75, 60, 200, 20);

            sex = new JComboBox<String>();//性别文本框
            sex.addItem("男");
            sex.addItem("女");
            sex.setFont(font);
            sex.setBounds(75, 90, 200, 20);

            dept = new JTextField();//系别文本框
            dept.setBounds(75, 120, 200, 20);

            degree = new JTextField();//职称文本框
            degree.setBounds(75, 150, 200, 20);

            phone = new JTextField();//电话文本框
            phone.setBounds(75, 180, 200, 20);

            password = new JPasswordField();//密码文本框
            password.setBounds(75, 210, 200, 20);

            noLabel = new JLabel("账号：");//账号标签
            noLabel.setFont(font);//设置字体
            noLabel.setBounds(25, 30, 50, 20);

            nameLabel = new JLabel("姓名：");//姓名标签
            nameLabel.setFont(font);//设置字体
            nameLabel.setBounds(25, 60, 50, 20);

            sexLabel = new JLabel("性别：");//性别标签
            sexLabel.setFont(font);//设置字体
            sexLabel.setBounds(25, 90, 50, 20);

            deptLabel = new JLabel("系别：");//系别标签
            deptLabel.setFont(font);//设置字体
            deptLabel.setBounds(25, 120, 50, 20);

            degreeLabel = new JLabel("职称：");//职称标签
            degreeLabel.setFont(font);//设置字体
            degreeLabel.setBounds(25, 150, 50, 20);

            phoneLabel = new JLabel("电话：");//电话标签
            phoneLabel.setFont(font);//设置字体
            phoneLabel.setBounds(25, 180, 50, 20);

            passwordLabel = new JLabel("新密码：");//密码标签
            passwordLabel.setFont(font);//设置字体
            passwordLabel.setBounds(25, 210, 50, 20);

            insertButton = new JButton("确定");
            insertButton.setFont(font);//设置字体
            insertButton.setForeground(Color.black);//设置颜色
            insertButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//手型鼠标
            insertButton.setBounds(100, 240, 100, 20);

            add(noLabel);
            add(no);
            add(nameLabel);
            add(name);
            add(sexLabel);
            add(sex);
            add(deptLabel);
            add(dept);
            add(passwordLabel);
            add(password);
            add(degreeLabel);
            add(degree);
            add(phoneLabel);
            add(phone);
            add(insertButton);
        }

        public void actionPerformed(ActionEvent e) {//登录监视器
            teacher.setId(no.getText());//得到文本框的账号
            teacher.setName(name.getText());//得到文本框的姓名
            teacher.setSex(sex.getSelectedItem().toString());//得到下拉列表的性别
            teacher.setDept(dept.getText());  //得到文本框的系别
            char[] pw = password.getPassword();//密码转为字符数组
            teacher.setPassword(new String(pw));//得到密码
            teacher.setDegree(degree.getText());//得到职称
            teacher.setPhone(phone.getText());//得到电话
            Refine refine = new Refine();
            refine.writeRefine(teacher);
            if(teacher.getSuccess())
                setVisible(false);
        }
}

