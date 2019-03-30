package com.zlw.onlinebookshop.entity;

/**
 * @author zlw
 * @create 2019-03-29 11:46
 */
public class Admin {
    private int AId;
    private String AName;
    private String APassword;
    private String AEmail;

    public int getAId() {
        return AId;
    }

    public void setAId(int AId) {
        this.AId = AId;
    }

    public String getAName() {
        return AName;
    }

    public void setAName(String AName) {
        this.AName = AName;
    }

    public String getAPassword() {
        return APassword;
    }

    public void setAPassword(String APassword) {
        this.APassword = APassword;
    }

    public String getAEmail() {
        return AEmail;
    }

    public void setAEmail(String AEmail) {
        this.AEmail = AEmail;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "AId=" + AId +
                ", AName='" + AName + '\'' +
                ", APassword='" + APassword + '\'' +
                ", AEmail='" + AEmail + '\'' +
                '}';
    }
}
