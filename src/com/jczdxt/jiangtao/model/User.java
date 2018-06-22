package com.jczdxt.jiangtao.model;
public class User {
    private boolean Identity = true;
    private boolean Success = false;
    private String id;
    private String password;
    public void setId(String id) {
        this.id = id;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getId() {
        return id;
    }
    public String getPassword() {
        return password;
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
