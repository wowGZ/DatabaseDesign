
package com.jczdxt.zhangmin.classes.dao;

import com.jczdxt.zhangmin.classes.Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements IUserDao{
    DBCon util = new DBCon();

    public boolean add(User user) {

        return util.update("insert into class(CLno,CLnum,CLFname,CLFphone) values(?,?,?,?)",
                user.getCLno(),user.getCLnum(),user.getCLFname(), user.getCLFphone()) > 0;
    }

    public boolean update(User user,String condition) {
        return util.update(
                "update class set CLno=?,CLnum=?,CLFname=?,CLFphone=?" + condition,
                user.getCLno(),user.getCLnum(),user.getCLFname(), user.getCLFphone()) > 0;
    }

    public boolean delete(String CLno) {

        return util.update("delete from class where CLno=?",CLno)>0;
    }

    public User QueryById(String CLno) {
        return user(util.query("select * from class where CLno=?", CLno));
    }

    public List<User> queryAll() {
        return  list(util.query("select * from class"));
    }

    private User user(ResultSet rs){
        User user = new User();
        try {
            if(rs.next()){
                user.setCLno(rs.getString("CLno"));
                user.setCLnum(rs.getInt("CLnum"));
                user.setCLFname(rs.getString("CLFname"));
                user.setCLFphone(rs.getString("CLFphone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private List<User> list(ResultSet rs){
        List<User> list=new ArrayList<User>();
        try {
            while(rs.next()){
                User user=new User();
                user.setCLno(rs.getString("CLno"));
                user.setCLnum(rs.getInt("CLnum"));
                user.setCLFname(rs.getString("CLFname"));
                user.setCLFphone(rs.getString("CLFphone"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}