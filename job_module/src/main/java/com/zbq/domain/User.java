package com.zbq.domain;

import java.util.Objects;

/**
 * @Author: zengbingqing2
 * @Description:
**/
public class User {

    private Long id;
    private String username;
    private String userIdNo;
    private String password;
    private Integer age;
    private String sex;
    private String phoneNum;
    private String homeTownAddress;
    private String email;
    private int pageSize = 3;
    private int offset =0;

    public User(long id, String username,String userIdNo,String password, int age, String sex, String phoneNum,String email){
        this.id=id;
        this.username=username;
        this.userIdNo=userIdNo;
        this.password=password;
        this.age=age;
        this.sex=sex;
        this.phoneNum=phoneNum;
        this.email=email;
    }

    public User(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

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

    public String getUserIdNo() {
        return userIdNo;
    }

    public void setUserIdNo(String userIdNo) {
        this.userIdNo = userIdNo;
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
        boolean a =  Objects.equals(username,other.username);
        boolean b= Objects.equals(userIdNo,other.userIdNo);
        return a && b;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
