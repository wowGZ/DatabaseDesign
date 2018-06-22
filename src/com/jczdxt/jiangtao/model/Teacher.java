package com.jczdxt.jiangtao.model;

public class Teacher {
    private boolean Identity =false;
    private boolean Success = false;
    private String id;
    private String name;
    private String sex;
    private String dept;
    private String password;
    private String degree;
    private String phone;
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public void setDept(String dept) {
        this.dept = dept;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getSex() {
        return sex;
    }
    public String getDept() {
        return dept;
    }
    public String getPassword() {
        return password;
    }
    public String getDegree() {
        return degree;
    }
    public String getPhone() {
        return phone;
    }
    public void setSuccess(boolean bo){
        Success = bo;
    }
    public boolean getSuccess() {
        return Success;
    }
    public void setIdentity(boolean bo){
        Identity =bo;
    }
    public boolean getIdentity(){
        return Identity;
    }
}

