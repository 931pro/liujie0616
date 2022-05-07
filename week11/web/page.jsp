<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022/4/28
  Time: 18:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>use</h2>
<%
pageContext.setAttribute("attName","in Page");
request.setAttribute("attName","in request");
session.setAttribute("attName","in request");
application.setAttribute("attName","in application");
%>
<h2>get</h2>
AttValue:<%=pageContext.getAttribute("attName")%><br>

</body>
</html>
