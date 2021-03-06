package com.jczdxt.jiangtao.iframe;



import com.jczdxt.jiangtao.dao.Delete;
import com.jczdxt.jiangtao.dao.Update;
import com.jczdxt.jiangtao.model.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PersonView extends JFrame implements ActionListener {
    private Teacher teacher;
    private JTextField no;//账号文本框
    private JTextField name;//姓名文本框
    private JTextField sex;//性别
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
    private JButton updateButton;//修改信息按钮
    private JButton deleteButton; //注销按钮
    int flag = 0;

    PersonView() {
        teacher = new Teacher();
        this.setTitle("个人中心");
        init();
        this.setBounds(0, 0, 320, 320);//大小及位置
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//关闭方式
        this.setLocationRelativeTo(null);//居中
        this.setVisible(true);//显示
        this.setResizable(true);//不可变换大小
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(flag ==0) {
                    name.setEditable(true);
                    dept.setEditable(true);
                    password.setEditable(true);
                    degree.setEditable(true);
                    phone.setEditable(true);
                    flag++;
                }
                else {
                    teacher.setName(name.getText());//得到文本框的姓名
                    teacher.setDept(dept.getText());  //得到文本框的系别
                    char[] pw = password.getPassword();//密码转为字符数组
                    teacher.setPassword(new String(pw));//得到密码
                    teacher.setDegree(degree.getText());//得到职称
                    teacher.setPhone(phone.getText());//得到电话
                    Update update = new Update();
                    update.writeUpdate(teacher);
                    if (teacher.getSuccess())
                        setVisible(false);
                }
            }
        });
        deleteButton.addActionListener(this);

    }

    public void init() {
        Font font = new Font("宋体", Font.PLAIN, 12);

        no = new JTextField();//账号文本框
        no.setText(MainView.no);
        no.setEditable(false);
        no.setBounds(75, 30, 200, 20);

        name = new JTextField();//姓名文本框
        name.setText(MainView.name);
        name.setEditable(false);
        name.setBounds(75, 60, 200, 20);

        sex = new JTextField();//性别文本框
        sex.setText(MainView.sex);
        sex.setEditable(false);
        sex.setBounds(75, 90, 200, 20);

        dept = new JTextField();//系别文本框
        dept.setText(MainView.dept);
        dept.setEditable(false);
        dept.setBounds(75, 120, 200, 20);

        password = new JPasswordField();//密码文本框
        password.setText(MainView.password);
        password.setEditable(false);
        password.setBounds(75, 150, 200, 20);

        degree = new JTextField();//职称文本框
        degree.setText(MainView.degree);
        degree.setEditable(false);
        degree.setBounds(75, 180, 200, 20);

        phone = new JTextField();//电话文本框
        phone.setText(MainView.phone);
        phone.setEditable(false);
        phone.setBounds(75, 210, 200, 20);

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

        passwordLabel = new JLabel("密码：");//密码标签
        passwordLabel.setFont(font);//设置字体
        passwordLabel.setBounds(25, 150, 50, 20);

        degreeLabel = new JLabel("职称：");//职称标签
        degreeLabel.setFont(font);//设置字体
        degreeLabel.setBounds(25, 180, 50, 20);

        phoneLabel = new JLabel("电话：");//电话标签
        phoneLabel.setFont(font);//设置字体
        phoneLabel.setBounds(25, 210, 50, 20);

        updateButton = new JButton("修改信息");
        updateButton.setFont(font);//设置字体
        updateButton.setForeground(Color.black);//设置颜色
        updateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//手型鼠标
        updateButton.setBounds(25, 240, 100, 20);

        deleteButton = new JButton("注销账号");
        deleteButton.setFont(font);//设置字体
        deleteButton.setForeground(Color.black);//设置颜色
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//手型鼠标
        deleteButton.setBounds(175, 240, 100, 20);
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
        add(updateButton);
        add(deleteButton);
    }
    public void actionPerformed(ActionEvent e){
        teacher.setId(no.getText());//得到文本框的账号
        char[] pw = password.getPassword();//密码转为字符数组
        teacher.setPassword(new String(pw));//得到密码
        Delete delete = new Delete();
        delete.writeDelete(teacher);
        if(teacher.getSuccess()) {
            setVisible(false);
        }
    }
}
