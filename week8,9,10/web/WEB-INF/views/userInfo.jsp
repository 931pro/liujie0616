<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 4/5/2021
  Time: 5:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page import="com.LiuJie.Model.User"%>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@include file="header.jsp"%>
<h1> User Info</h1>
<%
    User u =(User)session.getAttribute("user");
    DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
    String time=df.format(u.getBirthDate());
%>
<table>
    <tr>
        <td>Username:</td><td><%=u.getUsername()%></td>
    </tr><tr>
        <td>Password:</td><td><%=u.getPassword()%></td>
</tr><tr>
        <td>Email:</td><td><%=u.getEmail()%></td>
</tr><tr>
    <td>Gender:</td><td><%=u.getGender()%></td>
</tr><tr>
    <td>Birth Date:</td><td><%=time%></td>
</tr>
    <tr>
        <td><a href="updateUser">update</a></td>
    </tr>

</table>

<%@include file="footer.jsp"%>
