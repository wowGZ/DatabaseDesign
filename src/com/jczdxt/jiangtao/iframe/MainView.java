package com.jczdxt.jiangtao.iframe;

import com.jczdxt.chenweitao.iframe.ManageWindow;
import com.jczdxt.chenweitao.iframe.FFQKFrame;
import com.jczdxt.guozhen.iframe.JCZDFrame;
import com.jczdxt.zhangmin.teacher.IFrame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import static javax.swing.border.BevelBorder.RAISED;

public class MainView extends JFrame implements ActionListener{
    private JLabel groundLabel; //背景
    private JPanel jPanel;    //内容面板
    private JPanel statePanel;  //状态面板
    private JLabel stateLabel;  //状态标签
    private JLabel nameLabel;  //名称标签
    private JLabel nowDateLabel;  //时间标签
    private static JLabel id;  //身份标签
    private JSeparator jSeparator; //分隔符
    private JToolBar jToolBar;//工具栏
    private JButton orderButton;//征订按钮
    private JButton teacherButton;//教师信息按钮
    private JButton classButton;//班级信息按钮
    private JButton grantButton;//发放信息按钮
    private JButton personButton;//个人信息按钮
    private JButton exitButton;//退出按钮
    public static String name="XXX";//身份
    public static String no="";//教师工号
    public static String sex;
    public static String dept;
    public static String password;
    public static String degree;
    public static String phone;
    public static Font font1 = new Font("宋体",Font.PLAIN,19);
    public static Font font2 = new Font("宋体",Font.PLAIN,15);


    MainView(){
        this.setTitle("教材征订与发放系统");//标题
        init();//初始化
        this.setContentPane(jPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭方式
        this.setBounds(0, 0, 1000, 700);//大小及位置
        Image logoimage = new ImageIcon("C:\\Users\\郭朕\\Desktop\\数据库课程设计V1.1\\Study\\logo.jpg").getImage();
        this.setIconImage(logoimage);//图标设置
        this.setLocationRelativeTo(null);//居中
        this.setVisible(true);//显示
        this.setResizable(false);//不可变换大小
        if(LoginView.identity){
            orderButton.setVisible(false);
            personButton.setVisible(false);
            teacherButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //教师管理界面
                    new MainFrame().setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                }
            });
            classButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //班级管理界面
                    new com.jczdxt.zhangmin.classes.IFrame.MainFrame().setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                }
            });
            grantButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //教材发放界面
                    new ManageWindow().setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                }
            });
            exitButton.addActionListener(this);
        }
        else {
            classButton.setVisible(false);
            teacherButton.setVisible(false);
            orderButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //征订界面
                    new JCZDFrame().setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//                    new FFQKFrame().setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

                }
            });
            grantButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //发放信息界面
                    new FFQKFrame().setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                }
            });
            personButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //个人中心
                    new PersonView().setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                }
            });
            exitButton.addActionListener(this);
        }

    }
    public void initGroundLabel(){
        groundLabel = new JLabel();
        Image backgroundimage = new ImageIcon("C:\\Users\\郭朕\\Desktop\\数据库课程设计V1.1\\Study\\background2.jpg").getImage();
        groundLabel.setIcon(new ImageIcon(backgroundimage));
        groundLabel.setBounds(0,0,1000,700);//背景

    }
    public void initJToolBar(){
        jToolBar =new JToolBar("教材征订与发放系统");
        jToolBar.setFont(font1);
        jToolBar.setOrientation(SwingConstants.HORIZONTAL);
        jToolBar.setFloatable(false);

        orderButton = new JButton("教材征订");
        orderButton.setForeground(Color.black);
        Image orderimage = new ImageIcon("C:\\Users\\郭朕\\Desktop\\数据库课程设计V1.1\\Study\\order.jpg").getImage();
        orderButton.setIcon(new ImageIcon(orderimage));
        orderButton.setToolTipText("教材征订");
        orderButton.setFont(font1);
//        if(LoginView.identity)   //如果是管理员的话，字体变暗
//            orderButton.setForeground(Color.gray);

        teacherButton = new JButton("教师信息管理");
        teacherButton.setForeground(Color.black);
        Image teacherimage = new ImageIcon("C:\\Users\\郭朕\\Desktop\\数据库课程设计V1.1\\Study\\teacher.jpg").getImage();
        teacherButton.setIcon(new ImageIcon(teacherimage));
        teacherButton.setToolTipText("教师信息管理");
        teacherButton.setFont(font1);
//        if(!LoginView.identity)   //如果是教师的话，字体变暗
//            teacherButton.setForeground(Color.gray);

        classButton = new JButton("班级信息管理");
        classButton.setForeground(Color.black);
        Image classimage = new ImageIcon("C:\\Users\\郭朕\\Desktop\\数据库课程设计V1.1\\Study\\class.jpg").getImage();
        classButton.setIcon(new ImageIcon(classimage));
        classButton.setToolTipText("班级信息管理");
        classButton.setFont(font1);

        grantButton = new JButton("教材发放");
        grantButton.setForeground(Color.black);
        Image grantimage = new ImageIcon("C:\\Users\\郭朕\\Desktop\\数据库课程设计V1.1\\Study\\grant.jpg").getImage();
        grantButton.setIcon(new ImageIcon(grantimage));
        grantButton.setToolTipText("教材发放");
        grantButton.setFont(font1);
//        if(!LoginView.identity)   //如果是教师的话，字体变暗
//            grantButton.setForeground(Color.gray);

        personButton = new JButton("个人信息");
        personButton.setForeground(Color.black);
        Image personimage = new ImageIcon("C:\\Users\\郭朕\\Desktop\\数据库课程设计V1.1\\Study\\person.jpg").getImage();
        personButton.setIcon(new ImageIcon(personimage));
        personButton.setToolTipText("个人信息");
        personButton.setFont(font1);

        exitButton = new JButton("退出系统");
        exitButton.setForeground(Color.black);
        Image exitimage = new ImageIcon("C:\\Users\\郭朕\\Desktop\\数据库课程设计V1.1\\Study\\exit.jpg").getImage();
        exitButton.setIcon(new ImageIcon(exitimage));
        exitButton.setToolTipText("退出系统");
        exitButton.setFont(font1);

        jToolBar.add(orderButton);
        jToolBar.addSeparator();
        jToolBar.add(teacherButton);
        jToolBar.addSeparator();
        jToolBar.add(classButton);
        jToolBar.addSeparator();
        jToolBar.add(grantButton);
        jToolBar.addSeparator();
        jToolBar.add(personButton);
        jToolBar.addSeparator();
        jToolBar.add(exitButton);
        jToolBar.addSeparator();
    }
    public void initStatePanel(){

        GridBagConstraints gridBagConstraints1 = new GridBagConstraints();// 创建网格限制对象
        gridBagConstraints1.gridx = 2;// 组件位于网格的横向索引为2
        gridBagConstraints1.fill = GridBagConstraints.VERTICAL;// 组件垂直扩大以占据空白区域
        gridBagConstraints1.insets = new Insets(0, 5, 0, 5);// 组件彼此的间距
        gridBagConstraints1.gridy = 0;// 组件位于网格的纵向索引为0


        GridBagConstraints gridBagConstraints2 = new GridBagConstraints();// 创建网格限制对象
        gridBagConstraints2.gridx = 3;// 组件位于网格的横向索引为3
        gridBagConstraints2.gridy = 0;// 组件位于网格的纵向索引为0


        GridBagConstraints gridBagConstraints3 = new GridBagConstraints();// 创建网格限制对象
        gridBagConstraints3.gridx = 6;// 组件位于网格的横向索引为6
        gridBagConstraints3.fill = GridBagConstraints.VERTICAL;// 组件垂直扩大以占据空白区域
        gridBagConstraints3.insets = new Insets(0, 5, 0, 5);// 组件彼此的间距
        gridBagConstraints3.gridy = 0;// 组件位于网格的纵向索引为0


        GridBagConstraints gridBagConstraints4 = new GridBagConstraints();// 创建网格限制对象
        gridBagConstraints4.gridx = 5;// 组件位于网格的横向索引为5
        gridBagConstraints4.insets = new Insets(0, 5, 0, 5);// 组件彼此的间距
        gridBagConstraints4.gridy = 0;// 组件位于网格的纵向索引为0


        GridBagConstraints gridBagConstraints5 = new GridBagConstraints();// 创建网格限制对象
        gridBagConstraints5.gridx = 7;// 组件位于网格的横向索引为7
        gridBagConstraints5.weightx = 0.0;// 组件横向上不扩大
        gridBagConstraints5.fill = GridBagConstraints.NONE;// 组件不扩大
        gridBagConstraints5.gridy = 0;// 组件位于网格的纵向索引为0


        GridBagConstraints gridBagConstraints6 = new GridBagConstraints();// 创建网格限制对象
        gridBagConstraints6.gridx = 4;// 组件位于网格的横向索引为4
        gridBagConstraints6.fill = GridBagConstraints.VERTICAL;// 组件垂直扩大以占据空白区域
        gridBagConstraints6.weighty = 1.0;// 组件纵向扩大的权重是1.0
        gridBagConstraints6.insets = new Insets(0, 5, 0, 5);// 组件彼此的间距
        gridBagConstraints6.gridy = 0;// 组件位于网格的纵向索引为0


        GridBagConstraints gridBagConstraints = new GridBagConstraints();// 创建网格限制对象
        gridBagConstraints.gridx = 0;// 组件位于网格的横向索引为0
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;// 组件水平扩大以占据空白区域
        gridBagConstraints.weightx = 1.0;// 组件横向扩大的权重是1.0
        gridBagConstraints.gridy = 0;// 组件位于网格的纵向索引为0


        statePanel = new JPanel();// 状态面板
        statePanel.setLayout(new GridBagLayout());// 设置状态面板的布局
        statePanel.setBorder(BorderFactory.createBevelBorder(RAISED));// 设置状态面板的边框

        stateLabel = new JLabel();//窗体标签
        stateLabel.setText("教材征订与发放系统");

        if(LoginView.identity)
            id = new JLabel("您好! 管理员");
        else
            id = new JLabel("您好!"+name+"老师");
        id.setFont(font2);

        nowDateLabel = new JLabel();// “当前日期”标签
        Date now = new Date();// 创建Date对象
        nowDateLabel.setText(String.format("%tF", now));// 设置“当前日期”标签的文本内容
        nowDateLabel.setFont(font2);

        nameLabel = new JLabel("朕敏涛涛课设小组");// “名称”标签
        nameLabel.setFont(font2);

        jSeparator = new JSeparator();// 创建分隔符对象
        jSeparator.setOrientation(JSeparator.VERTICAL);// 竖直分隔符

        statePanel.add(stateLabel, gridBagConstraints);// 向状态面板中添加选定窗体状态标签
        statePanel.add(jSeparator, gridBagConstraints6);// 向状态面板中添加分隔符
        statePanel.add(nameLabel, gridBagConstraints5);// 向状态面板中添加“名称”标签
        statePanel.add(jSeparator, gridBagConstraints3);// 向状态面板中添加分隔符
        statePanel.add(nowDateLabel, gridBagConstraints4);// 向状态面板中添加“当前日期”标签
        statePanel.add(id, gridBagConstraints2);// 向状态面板中添加“身份”标签
        statePanel.add(jSeparator, gridBagConstraints1);// 向状态面板中添加分隔符
    }
    public void init(){
        initGroundLabel();
        initJToolBar();
        initStatePanel();
        jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.add(statePanel,BorderLayout.SOUTH);
        jPanel.add(jToolBar,BorderLayout.NORTH);
        jPanel.add(groundLabel,BorderLayout.CENTER);
    }
    public void actionPerformed(ActionEvent e){
        this.setVisible(false);
    }
}
