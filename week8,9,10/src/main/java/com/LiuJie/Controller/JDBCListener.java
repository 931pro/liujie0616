package com.LiuJie.Controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class JDBCListener implements ServletContextListener {
    Connection con=null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //在app启动前加载数据库

        ServletContext context = sce.getServletContext();
        String driver = context.getInitParameter("driver");
        String url = context.getInitParameter("url");
        String username = context.getInitParameter("username");
        String password = context.getInitParameter("password");
//        System.out.println(url);
//        System.out.println(username);
//        System.out.println(password);

        try {
            Class.forName(driver);
            this.con = DriverManager.getConnection(url, username, password);
            System.out.println("init()--> " + this.con);
        } catch (SQLException | ClassNotFoundException var8) {
            var8.printStackTrace();
        }

        context.setAttribute("con", this.con);
    }
}
