package com.jczdxt.guozhen.iframe;

import com.jczdxt.guozhen.dao.Query;
import com.jczdxt.jiangtao.iframe.MainView;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class JCZDFrame extends JFrame implements ActionListener, TreeSelectionListener {
    private JToolBar jToolBar;
    private JButton JCZDJButton;
    private JButton FFQKJButton;

    private JPanel mainJPanel;

    private JPanel jPanelNorth;
    private JLabel helloJLabel;

    private JPanel jPanelCenter;
    private JPanel jPanelCenterEast;
    private JTree classCourseJTree;
    private String[] column;
    private String[][] content;
    private JScrollPane tableView;
    private JTable showCourseBookJTable;
    private JLabel showMessageJLabel;
    private DefaultTableModel tableModel;
    private Vector<String> columnNames;
    private Vector<Vector<String>> records;

    private JPanel jPanelSouth;
    private JButton confirmJButton;
    private JButton deleteJButton;
    private JButton searchJButton;

    private void initToolBar() {
        jToolBar = new JToolBar();
        jToolBar.setFont(MainView.font1);

        JCZDJButton = new JButton("教材征订");
        JCZDJButton.setToolTipText("教材征订");
        JCZDJButton.setFont(MainView.font1);
        JCZDJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        FFQKJButton = new JButton("发放情况");
        FFQKJButton.setToolTipText("发放情况");
        FFQKJButton.setFont(MainView.font1);

        jToolBar.add(JCZDJButton);
        jToolBar.addSeparator();
        jToolBar.add(FFQKJButton);
        jToolBar.addSeparator();
    }

    private void initJTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("您所带的班级");
        Query treeResult = new Query();
        treeResult.setSQL("select distinct CLno from teacher_class_course_book " +
                "where Tno="+ MainView.no);//更改教师工号即可实现不同的效果，既可以根据教师不同显示各自所带班级的课程
        content = treeResult.getRecord();
        column = treeResult.getColumnName();
        for (int i = 0; i < content.length; i++) {
            for (int j = 0; j < content[i].length; j++) {
                DefaultMutableTreeNode classNode = new DefaultMutableTreeNode(content[i][j]);
                root.add(classNode);
                treeResult.setSQL("select Cname from course,teacher_class_course_book " +
                        "where teacher_class_course_book.Cno=course.Cno and CLno=" + content[i][j] + " and teacher_class_course_book.Tno=" + MainView.no);
                String[][] strings = treeResult.getRecord();
                for (int k = 0; k < strings.length; k++) {
                    for (int l = 0; l < strings[k].length; l++) {
                        DefaultMutableTreeNode courseNode = new DefaultMutableTreeNode(strings[k][l], false);
                        classNode.add(courseNode);
                    }
                }
            }
        }
        classCourseJTree = new JTree(root);
        classCourseJTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        classCourseJTree.addTreeSelectionListener(this);
        jPanelCenter.add(classCourseJTree, BorderLayout.WEST);
    }

    private void initJTable() {
        showMessageJLabel = new JLabel(" ");
        jPanelCenterEast.add(showMessageJLabel, BorderLayout.NORTH);
        Query resultContent = new Query();
        resultContent.setSQL("select * from book");
        records = resultContent.getRecords();
        columnNames = resultContent.getColumnNames();
        tableModel = new DefaultTableModel(records, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
//        showCourseBookJTable = new JTable(records, columnNames);
        showCourseBookJTable = new JTable(tableModel);
        showCourseBookJTable.setRowSorter(new TableRowSorter<>(tableModel));
        showCourseBookJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tableView = new JScrollPane(showCourseBookJTable);
        jPanelCenterEast.add(tableView, BorderLayout.CENTER);
        jPanelCenter.add(jPanelCenterEast, BorderLayout.EAST);
    }

    private void initNorth() {

        helloJLabel = new JLabel("欢迎使用本教材征订与发放管理系统");
        jPanelNorth = new JPanel(new BorderLayout());
        jPanelNorth.add(helloJLabel, BorderLayout.NORTH);

    }

    private void initCenter() {
        jPanelCenter = new JPanel(new BorderLayout());
        jPanelCenterEast = new JPanel(new BorderLayout());
        initJTree();
        initJTable();

    }

    private void initSouth() {
        confirmJButton = new JButton("确定");
        confirmJButton.addActionListener(this);

        deleteJButton = new JButton("删除该班级所选课程");
        deleteJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Query query = new Query();

                query.setSQL("select teacher_class_course_book.condition " +
                        "from teacher_class_course_book,course " +
                        "where teacher_class_course_book.Cno=course.Cno " +
                        "and teacher_class_course_book.CLno="+ CLno + " and course.Cname='" + courseName + "'");
                content = query.getRecord();
                if (content[0][0].equals("已发放")){
                    JFrame frame = new JFrame("提示");
                    frame.setVisible(true);
                    frame.setBounds(0,0,200,100);
                    frame.setLocationRelativeTo(null);
                    frame.add(new JLabel("教材已发放不可删除！"));
                } else {
                    query.setSQL("Update teacher_class_course_book set Bno=0 " +
                            "where teacher_class_course_book.Tno=" + MainView.no + " " +
                            "and teacher_class_course_book.CLno=" + CLno + " " +
                            "and teacher_class_course_book.Cno=" + Cno);
                    query.startUpdate();
                    JFrame frame = new JFrame("提示");
                    frame.setVisible(true);
                    frame.setBounds(0, 0, 200, 100);
                    frame.setLocationRelativeTo(null);
                    frame.add(new JLabel("删除成功！"));
                    showMessageJLabel.setText(CLno + "班的" + courseName + "课尚未征订教材");
                }

            }
        });

        searchJButton = new JButton("书籍查找界面");
        searchJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchFrame().setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            }
        });
        jPanelSouth = new JPanel();
        jPanelSouth.add(confirmJButton);
        jPanelSouth.add(deleteJButton);
        jPanelSouth.add(searchJButton);
    }

    public JCZDFrame() throws HeadlessException {
        initToolBar();
        initNorth();
        initCenter();
        initSouth();
        setLayout(new BorderLayout());
//        add(jToolBar, BorderLayout.NORTH);
        mainJPanel = new JPanel(new BorderLayout());
        mainJPanel.setLayout(new BorderLayout());
        mainJPanel.add(jPanelNorth, BorderLayout.NORTH);
        mainJPanel.add(jPanelCenter, BorderLayout.CENTER);
        mainJPanel.add(jPanelSouth, BorderLayout.SOUTH);
        getContentPane().add(mainJPanel,BorderLayout.CENTER);
        setVisible(true);
        setBounds(10, 12, 615, 400);
        setTitle("教材征订");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    private String CLno;
    private String courseName;
    private String Cno;
    private String bookName;

    @Override
    public void actionPerformed(ActionEvent e) {

        Query query = new Query();
        query.setSQL("select teacher_class_course_book.condition " +
                "from teacher_class_course_book,course " +
                "where teacher_class_course_book.Cno=course.Cno " +
                "and teacher_class_course_book.CLno="+ CLno + " and course.Cname='" + courseName + "'");
        content = query.getRecord();
        if (content[0][0].equals("已发放")){
            JOptionPane.showMessageDialog(this,"本教材已经发放，不可重新征订！");
        } else {
            query.setSQL("select Cno from course where Cname='" + courseName + "'");
            content = query.getRecord();

            query.setSQL("update teacher_class_course_book set Bno=" +
                    showCourseBookJTable.getValueAt(showCourseBookJTable.getSelectedRow(), 0).toString() +
                    " where CLno=" + CLno + " and Cno=" + content[0][0]);
            bookName = showCourseBookJTable.getValueAt(showCourseBookJTable.getSelectedRow(), 1).toString();
            query.startUpdate();
            JOptionPane.showMessageDialog(this, "修改成功！已经为" + CLno + "班的" +
                    courseName + "征订定了教材《" + bookName + "》");
//        jPanelCenterEast.validate();

            System.out.println("1");
        }

    }


    @Override
    public void valueChanged(TreeSelectionEvent e) {
        if (!classCourseJTree.isSelectionEmpty()) {
            TreePath treePath = classCourseJTree.getSelectionPath();
            Object[] path = treePath.getPath();

            if (path.length == 3) {

                DefaultMutableTreeNode node = (DefaultMutableTreeNode) path[2];
                DefaultMutableTreeNode node2 = (DefaultMutableTreeNode) path[1];
                CLno = node2.getUserObject().toString();
                System.out.println(CLno);
                courseName = node.getUserObject().toString();
                System.out.println(courseName);
                Query query = new Query();
                query.setSQL("select Cno from course where Cname='" + courseName + "'");
                content = query.getRecord();
                Cno = content[0][0];


                try {
                    Query condition = new Query();
                    condition.setSQL("select Bno from teacher_class_course_book,course " +
                            "where course.Cname='" + courseName +
                            "' and teacher_class_course_book.CLno=" + CLno +
                            " and teacher_class_course_book.Cno=course.Cno");
                    content = condition.getRecord();
                    System.out.println(content[0][0]);
                    if (content[0][0].equals("0")) {
                        showMessageJLabel.setText(CLno + "班的" + courseName + "课尚未征订教材");
                    } else {
                        condition.setSQL("select Bname from book where Bno=" + content[0][0]);
                        content = condition.getRecord();
                        showMessageJLabel.setText(CLno + "班的" + courseName + "课已经征订了教材《" + content[0][0] + "》");
                    }
                } catch (NullPointerException e1) {
                    showMessageJLabel.setText(CLno + "班的" + courseName + "课尚未征订教材");
                }


                Query resultRecord = new Query();
                resultRecord.setSQL("select book.Bno,book.Bname,book.publish,book.author " +
                        "from book,course,course_book " +
                        "where course.Cno=course_book.Cno and course_book.Bno=book.Bno and Cname='" + courseName + "'");
                records = resultRecord.getRecords();
                columnNames = resultRecord.getColumnNames();
                tableModel = new DefaultTableModel(records, columnNames) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                jPanelCenterEast.remove(tableView);
                tableView.remove(showCourseBookJTable);
                showCourseBookJTable = new JTable(tableModel);
                showCourseBookJTable.setRowSorter(new TableRowSorter<>(tableModel));
                tableView = new JScrollPane(showCourseBookJTable);
                jPanelCenterEast.add(tableView, BorderLayout.CENTER);
                jPanelCenterEast.validate();
//                jPanelCenter.add(jPanelCenterEast, BorderLayout.EAST);
//                jPanelCenter.validate();

            }
            System.out.println("success");
            System.out.println();
        }
    }
}
