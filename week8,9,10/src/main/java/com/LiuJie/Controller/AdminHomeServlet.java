package com.LiuJie.Controller;

import com.LiuJie.Model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AdminHomeServlet", value = "/admin/home")
public class AdminHomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                HttpSession session=request.getSession();
                if(session!=null&&session.getAttribute("user")!=null){
                     User user=(User)session.getAttribute("user");
                     if("admin".equals(user.getUsername())){
                         request.getRequestDispatcher("../WEB-INF/views/admin/index.jsp").forward(request,response);
                     }else{
                         //not admin user
                         session.invalidate();//kill session right now
                         request.setAttribute("message","Unauthorized Access Admin Module!!!");
                         request.getRequestDispatcher("../WEB-INF/views/login.jsp").forward(request,response);
                     }
                }else{//no session
                    request.setAttribute("message","Please login as Admin");
                    request.getRequestDispatcher("../WEB-INF/views/login.jsp");

                }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
