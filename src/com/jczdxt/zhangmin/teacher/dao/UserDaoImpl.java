package com.jczdxt.zhangmin.teacher.dao;

import com.jczdxt.zhangmin.teacher.Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements IUserDao{
    DBCon util = new DBCon();

    @Override
    public boolean add(User user) {

        return util.update("insert into teacher values(?,?,?,?,?,?,?)",
                user.getTno(),user.getTname(),user.getTsex(), user.getTdept(), user.getTdegree(),user.getTphone(),user.getTpassword()) > 0;
    }

    @Override
    public boolean update(User user, String condition) {
        return util.update(
                "update teacher set Tname=?,Tsex=?,Tdept=?,Tdegree=?,Tphone=?,Tpassword=?" + condition,
                user.getTname(),user.getTsex(), user.getTdept(), user.getTdegree(),user.getTphone(),user.getTpassword()) > 0;
    }

    @Override
    public boolean delete(String Tno) {

        return util.update("delete from teacher where Tno=?", Tno)>0;
    }

    @Override
    public User QueryById(String Tno) {
        return _user(util.query("select * from teacher where Tno=?", Tno));
    }

    @Override
    public List<User> queryAll() {
        return  _list(util.query("select * from teacher"));
    }

    private User _user(ResultSet rs){
        User user = new User();
        try {
            if(rs.next()){
                user.setTno(rs.getString("Tno"));
                user.setTname(rs.getString("Tname"));
                user.setTsex(rs.getString("Tsex"));
                user.setTdept(rs.getString("Tdept"));
                user.setTdegree(rs.getString("Tdegree"));
                user.setTphone(rs.getString("Tphone"));
                user.setTpassword(rs.getString("Tpassword"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private List<User> _list(ResultSet rs){
        List<User> _list=new ArrayList<User>();
        try {
            while(rs.next()){
                User user=new User();
                user.setTno(rs.getString("Tno"));
                user.setTname(rs.getString("Tname"));
                user.setTsex(rs.getString("Tsex"));
                user.setTdept(rs.getString("Tdept"));
                user.setTdegree(rs.getString("Tdegree"));
                user.setTphone(rs.getString("Tphone"));
                user.setTpassword(rs.getString("Tpassword"));
                _list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return _list;
    }

}