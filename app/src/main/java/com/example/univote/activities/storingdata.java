package com.example.univote.activities;

public class storingdata {
    String fullname;
    String email;
    String batchno;
    String password;

    public storingdata(){

    }

    public storingdata(String fullname, String email, String batchno, String password) {
        this.fullname = fullname;
        this.email = email;
        this.batchno = batchno;
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBatchno() {
        return batchno;
    }

    public void setBatchno(String batchno) {
        this.batchno = batchno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
