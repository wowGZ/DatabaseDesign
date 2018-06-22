package com.jczdxt.jiangtao.iframe;

import com.jczdxt.jiangtao.dao.Login;
import com.jczdxt.jiangtao.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class LoginView extends JFrame implements ActionListener {
    private User user;
    private JTextField no;//账号文本框
    private JPasswordField password;//密码文本框
    private JLabel groundLabel;//背景
    private JLabel idLabel;//账号标签
    private JLabel passwordLabel;//密码标签
    private JButton insertButton;//注册
    private JButton refineButton;//找回密码
    private JButton loginButton;//登录
    private JComboBox<String> id;//身份选择
    private JTextField sex;
    private  JLabel sexLabel;
    static boolean identity = true ;//身份
    LoginView() {
        user = new User();
        this.setTitle("登录");//标题
        init();//初始化
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭方式
        this.setLayout(null);//布局
        this.setBounds(0, 0, 700, 400);//大小及位置
        Image logoimage = new ImageIcon("C:\\Users\\郭朕\\Desktop\\数据库课程设计V1.1\\Study\\logo.jpg").getImage();
        this.setIconImage(logoimage);//图标设置
        this.setLocationRelativeTo(null);//居中
        this.setVisible(true);//显示
        this.setResizable(false);//不可变换大小
        loginButton.addActionListener(this);//登录监视器
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InsertView();
            }
        });//注册监视器
        id.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(id.getSelectedItem().toString().equals("管理员")) {
                    user.setIdentity(true);
                    identity = true;
                }
                else {
                    user.setIdentity(false);
                    identity = false;
                }
            }
        });  //身份选择监视器
        refineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RefineView();
            }
        });  //找回密码监视器
    }
    public void init(){//初始化
        Font font = new Font("宋体",Font.PLAIN,25);

        Container container = this.getContentPane();
        groundLabel = new JLabel();
        Image backgroundimage = new ImageIcon("C:\\Users\\郭朕\\Desktop\\数据库课程设计V1.1\\Study\\background.jpg").getImage();
        groundLabel.setIcon(new ImageIcon(backgroundimage));
        groundLabel.setBounds(0,0,700,400);//背景

        no = new JTextField();//账号文本框
        no.setBounds(250,100,400,40);

        password = new JPasswordField();//密码文本框
        password.setBounds(250,160,400,40);

        //sex = new JTextField();
       // sex.setBounds(250,600,400,40);

        idLabel = new JLabel("账号：");//账号标签
        idLabel.setFont(font);//设置字体
        idLabel.setBounds(50,100,100,50);

        passwordLabel = new JLabel("密码：");//密码标签
        passwordLabel.setFont(font);//设置字体

        //sexLabel = new JLabel("性别：");
       // sexLabel.setFont(font);
        //sexLabel.setBounds(50,600,100,50);

        passwordLabel.setBounds(50,160,100,50);

        insertButton = new JButton("注册账号");
        insertButton.setFont(font);//设置字体
        insertButton.setForeground(Color.black);//设置颜色
        insertButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//手型鼠标
        insertButton.setBounds(300,220,150,50);

        refineButton = new JButton("找回密码");
        refineButton.setFont(font);//设置字体
        refineButton.setForeground(Color.black);//设置颜色
        refineButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//手型鼠标
        refineButton.setBounds(500,220,150,50);

        loginButton = new JButton("登      录");
        loginButton.setFont(font);//设置字体
        loginButton.setForeground(Color.black);//设置颜色
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//手型鼠标
        loginButton.setBounds(50,280,600,50);

        id = new JComboBox<String>();
        id.addItem("管理员");
        id.addItem("教师");
        id.setFont(font);
        id.setBounds(50,220,200,50);
        groundLabel.add(idLabel);
        groundLabel.add(passwordLabel);
       // groundLabel.add(sexLabel);
        groundLabel.add(insertButton);
        groundLabel.add(refineButton);
        groundLabel.add(loginButton);
        groundLabel.add(id);
        container.add(groundLabel);
        container.add(no);
        container.add(password);
      //  container.add(sex);
    }
    public static void main(String args[]){
        new LoginView();
//        new FFQKFrame();
    }
    public void actionPerformed(ActionEvent e) {//登录监视器
        user.setId(no.getText());//得到文本框的账号
        char[] pw = password.getPassword();//密码转为字符数组
        user.setPassword(new String(pw));//得到密码
        Login login = new Login();
        user = login.loading(user);
        if(user.getSuccess()) {
            setVisible(false);
            new MainView();
        }
    }
}