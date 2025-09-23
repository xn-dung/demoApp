package com.example.myapplication.model;

public class User {
    private int Id;
    private String username;
    private String password;
    private String fullname;
    private String address;
    private String email;
    private String tel;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User(String username, String password, String fullname, String address, String email, String tel) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.address = address;
        this.email = email;
        this.tel = tel;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;

    }

    public String getFullname() {
        return fullname;
    }

    public String getTel() {
        return tel;
    }
    public String getEmail() {
        return email;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
