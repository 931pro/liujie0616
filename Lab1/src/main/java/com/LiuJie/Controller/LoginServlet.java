package com.LiuJie.Controller;

import com.LiuJie.Dao.UserDao;
import com.LiuJie.Model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    Connection con=null;

    @Override
    public void init() throws ServletException {
        super.init();
        con=(Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String username=request.getParameter("username");
                String password=request.getParameter("password");
                UserDao userdao=new UserDao();
        try {
           User user= userdao.findByUsernamePassword(con,username,password);
            if(user!=null){
                    //把user放到request里
                //cookie
                //step1:创建cookie
//                Cookie c =new Cookie("sessionid",String.valueOf(user.getId()));   //sessionid == userid
                //step2: set age of cookie
//                c.setMaxAge(10*60);// 10 min
                // step3:add cookie into response
//                response.addCookie(c);

                // code remember me
                String rememberMe=request.getParameter("rememberMe");
                if(rememberMe!=null&&rememberMe.equals("1")){
                    Cookie usernameCookie =new Cookie("cUsername",user.getUsername());
                    Cookie passwordCookie =new Cookie("cPassword",user.getPassword());
                    Cookie rememberMeCookie = new Cookie("cRememberMe",rememberMe);

                    //set ages
                    usernameCookie.setMaxAge(5);
                    passwordCookie.setMaxAge(5);
                    rememberMeCookie.setMaxAge(5);
                    response.addCookie(usernameCookie);
                    response.addCookie(passwordCookie);
                    response.addCookie(rememberMeCookie);
            }

                //session
                HttpSession session = request.getSession();
                System.out.println("session id-->"+session.getId());
                session.setMaxInactiveInterval(10*60);
                session.setAttribute("user",user);

//                request.setAttribute("user",user);
                request.getRequestDispatcher("WEB-INF/views/userInfo.jsp").forward(request,response);
            }
            else {
                //在数据库没找到

                request.setAttribute("message","username or password is ERROR!!!");
                request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
