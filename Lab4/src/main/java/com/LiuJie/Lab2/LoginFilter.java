package com.LiuJie.Lab2;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "LoginFilter",urlPatterns = {"/Lab2/validate.jsp","/Lab2/welcome.jsp"})
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
        System.out.println("i am in LoginFilter--init()");
    }

    public void destroy() {
        System.out.println("i am in LoginFilter--destroy()");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("i am in LoginFilter--doFilter() request before chain");
        HttpServletRequest req=(HttpServletRequest) request;
        HttpServletResponse res=(HttpServletResponse) response;
        HttpSession session=req.getSession(false);

        System.out.println(session.getAttribute("user"));
        if(session.getAttribute("user")==null)req.getRequestDispatcher("login.jsp").forward(req,res);

        chain.doFilter(request, response);
        System.out.println("i am in LoginFilter--destroy() request after chain");
    }
}
