package com.example.myapplication.data.dao;



import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
    public static Connection con;

    public DAO() {
        if (con == null) {
            String dbUrl = "jdbc:mysql://localhost:3306/appProject?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&characterEncoding=UTF-8";
            String dbClass = "com.mysql.cj.jdbc.Driver";

            try {
                Class.forName(dbClass);
                con = DriverManager.getConnection(dbUrl, "sa", "1");
                System.out.println("Kết nối CSDL thành công");
            } catch (Exception e) {
                System.out.println("Kết nối CSDL thất bại");
                e.printStackTrace();
            }
        }
    }
}
