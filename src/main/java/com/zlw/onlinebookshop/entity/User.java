package com.zlw.onlinebookshop.entity;

/**
 * @author zlw
 * @create 2019-03-28 11:36
 */
public class User {
    private int UId;
    private String UName;
    private String UPassword;
    private String UEmail;

    public int getUId() {
        return UId;
    }

    public void setUId(int UId) {
        this.UId = UId;
    }

    public String getUName() {
        return UName;
    }

    public void setUName(String UName) {
        this.UName = UName;
    }

    public String getUPassword() {
        return UPassword;
    }

    public void setUPassword(String UPassword) {
        this.UPassword = UPassword;
    }

    public String getUEmail() {
        return UEmail;
    }

    public void setUEmail(String UEmail) {
        this.UEmail = UEmail;
    }

    @Override
    public String toString() {
        return "User{" +
                "UId=" + UId +
                ", UName='" + UName + '\'' +
                ", UPassword='" + UPassword + '\'' +
                ", UEmail='" + UEmail + '\'' +
                '}';
    }
}
