<%@page import="com.LiuJie.Model.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022/4/25
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
<h1>User Update</h1>

<!-- form-->
<%
    User u = (User)session.getAttribute("user");
    DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
    String time=df.format(u.getBirthDate());
%>

<form method="post" action="updateUser">
    <input type="hidden" name="id" value="<%= u.getId()%>">
    <input type="text" name="username" value="<%= u.getUsername()%>">
    <input type="password" name="password" value="<%= u.getPassword()%>">
    <input type="text" name="email" value="<%= u.getEmail()%>">
    <input type="radio" name="gender" value="male" <%= "male".equals(u.getGender())?"checked":""%>>male<br/>
    <input type="radio" name="gender" value="female" <%= "female".equals(u.getGender())?"checked":""%>>female<br/>
    <input type="text" name="birthDate" value="<%= time%>">
    <input type="submit" value="Save Change ">
</form>
