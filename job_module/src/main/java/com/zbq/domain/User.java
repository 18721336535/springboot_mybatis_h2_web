package com.zbq.domain;

import java.util.Objects;

/**
 * @Author: zengbingqing2
 * @Description:
**/
public class User {

    private Long id;
    private String userName;
    private String userIdNo;
    private String userSex;
    private int userAge;
    private String phoneNum;
    private String homeTownAddress;
    private String email;
    private int pageSize = 3;
    private int offset =0;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIdNo() {
        return userIdNo;
    }

    public void setUserIdNo(String userIdNo) {
        this.userIdNo = userIdNo;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getHomeTownAddress() {
        return homeTownAddress;
    }

    public void setHomeTownAddress(String homeTownAddress) {
        this.homeTownAddress = homeTownAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode(){
        int result = 1;
        int prime = 3;
        return result*prime+ (userIdNo == null ? 0:userIdNo.hashCode());
    }
    @Override
    public boolean equals(Object obj){
        User other = (User)obj;
        boolean a =  Objects.equals(userName,other.userName);
        boolean b= Objects.equals(userIdNo,other.userIdNo);
        return a && b;
    }


}
