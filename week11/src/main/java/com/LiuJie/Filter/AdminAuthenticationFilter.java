package com.LiuJie.Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "AdminAuthenticationFilter")
public class AdminAuthenticationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //change ServletRequest to HttpServletRequest
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        //get session
        HttpSession session = httpRequest.getSession(false);
        boolean isLoggedIn = (session!=null && session.getAttribute("user")!=null);
        String loginURI = httpRequest.getContextPath()+"/admin/login";
        boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
        boolean isLoginPage = httpRequest.getRequestURI().endsWith("login");
        if (isLoggedIn && (isLoginRequest || isLoginPage)){
            //the admin is already login ande he is trying login again
            //then forward to the admin homepage
            request.getRequestDispatcher("/admin/home").forward(httpRequest,httpResponse);//go to home

        }else if (isLoggedIn || isLoginRequest){

            chain.doFilter(request,response);//go to next destination
        }else {
            System.out.println(httpRequest.getContextPath());
            httpResponse.sendRedirect(httpRequest.getContextPath()+"/admin/login");//go to login
        }
    }
}
