package com.LiuJie.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dasd {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url="jdbc:sqlserver://localhost:1433;database=userdb;encrypt=false";
        String username="sa";
        String password="225514";
        Class.forName(driver);
        Connection con=null;
        con= DriverManager.getConnection(url,username,password);
        if(con==null) System.out.println("err");
        else System.out.println("suc");
    }
}
