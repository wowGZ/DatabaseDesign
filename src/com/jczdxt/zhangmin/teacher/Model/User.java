package com.jczdxt.zhangmin.teacher.Model;

import java.io.Serializable;

public class User implements Serializable {
    private String Tno;
    private String Tname;
    private String Tsex;
    private String Tdept;
    private String Tdegree;
    private String Tphone;
    private String Tpassword;

    public String getTpassword() {
        return Tpassword;
    }

    public void setTpassword(String tpassword) {
        Tpassword = tpassword;
    }

    public String getTno() {
        return Tno;
    }
    public void setTno(String tno) {
        Tno = tno;
    }

    public String getTname() {
        return Tname;
    }

    public void setTname(String tname) {
        Tname = tname;
    }

    public String getTsex() {
        return Tsex;
    }

    public void setTsex(String tsex) {
        Tsex = tsex;
    }

    public String getTdept() {
        return Tdept;
    }

    public void setTdept(String tdept) {
        Tdept = tdept;
    }

    public String getTdegree() {
        return Tdegree;
    }

    public void setTdegree(String tdegree) {
        Tdegree = tdegree;
    }

    public String getTphone() {
        return Tphone;
    }

    public void setTphone(String tphone) {
        Tphone = tphone;
    }


}