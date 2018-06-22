package com.jczdxt.zhangmin.classes.dao;

import com.jczdxt.zhangmin.classes.Model.User;

import java.util.List;

public interface IUserDao {

    boolean add(User user);

    boolean update(User user, String s);

    boolean delete(String Tno);

    User QueryById(String Tno);

    List<User> queryAll();
}