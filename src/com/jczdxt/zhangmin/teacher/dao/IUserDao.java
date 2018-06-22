package com.jczdxt.zhangmin.teacher.dao;

import com.jczdxt.zhangmin.teacher.Model.User;

import java.util.List;

public interface IUserDao {

    boolean add(User user);

    boolean update(User user, String s);

    boolean delete(String Tno);

    User QueryById(String Tno);

    List<User> queryAll();
}