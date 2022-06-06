
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022/6/6
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<sql:setDataSource var="myDS" driver="com.microsoft.sqlserver.jdbc.SQLServerDriver" url="jdbc:sqlserver://localhost:1433;database=userdb;" user="sa" password="225514" />

    <sql:query var="selectUsers" dataSource="${myDS}">select *from usertable where username='${param.username}'  and password='${param.password}'</sql:query>
<%
    //
int id=0;
%>
<c:forEach var="s" items="${selectUsers.rows}">
   <%id++;%>
</c:forEach>
<c:set var="a" value="<%=id%>"></c:set>
<c:choose>
    <c:when test="${a>0}">
        <jsp:forward page="welcome.jsp"></jsp:forward>
    </c:when>
    <c:otherwise>
        <h3>your password or username is wrong!!!</h3>

        <jsp:include page="login.jsp"></jsp:include>
    </c:otherwise>
</c:choose>
</body>
</html>
