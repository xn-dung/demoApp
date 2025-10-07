package com.example.myapplication.model;
import java.io.Serializable;
import java.util.Objects;
public class User implements Serializable {
    private String id;
    private String username;
    private String password;
    private String name;
    private String address;
    private String email;
    private String tel;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User(String id,String username, String password, String name, String address, String email, String tel) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.email = email;
        this.tel = tel;
    }
    public User(String username, String password, String name, String address, String email, String tel) {
        this.username = username;
        this.password = password;
        this.name = name;
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
        return name;
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
        this.name = fullname;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    @Override
    public int hashCode(){
        return Objects.hash(username,password,name,address,email,tel);
    }
    @Override
    public boolean equals(Object o ) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        User user = (User) o;

        return Objects.equals(username,user.username) && Objects.equals(password,user.password) && Objects.equals(name,user.name) && Objects.equals(address,user.address) && Objects.equals(email,user.email) && Objects.equals(tel,user.tel);

    }

    public String getId() {
        return id;
    }
}
