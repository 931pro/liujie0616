<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022/6/6
  Time: 9:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lab4</title>
</head>
<body>
<form action="register.jsp">
    username <input type="text" name="username"  ><br/>
    password <input type="password" name="password"><br/>
    email <input type="text" name="email" ><br/>
    gender <input type="radio" name="gender" value="male">male<input type="radio" name="gender" value="female">female<br/>
    date of birth <input type="text" name="birthDate"><br/>
    <input type="submit" value="register">
</form>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!--create connection-->
<sql:setDataSource var="myDS" driver="com.microsoft.sqlserver.jdbc.SQLServerDriver" url="jdbc:sqlserver://localhost:1433;database=userdb;" user="sa" password="225514" />
<!--insert-->
<c:if test="${!empty param.username}">
    <sql:update var="newUser" dataSource="${myDS}">
        insert into usertable values(?,?,?,?,?)
        <sql:param value="${param.username}"/>
        <sql:param value="${param.password}"/>
        <sql:param value="${param.email}"/>
        <sql:param value="${param.gender}"/>
        <sql:param value="${param.birthDate}"/>
    </sql:update>

</c:if>
<!--select-->
<sql:query var="selectUsers" dataSource="${myDS}">select *from usertable</sql:query>
<table>
    <tr></tr>
    <c:forEach var="row" items="${selectUsers.rows}">
        <tr>
        <td><c:out value="${row.id}"/></td>
        <td><c:out value="${row.username}"/></td>
        <td><c:out value="${row.password}"/></td>
        <td><c:out value="${row.email}"/></td>
        <td><c:out value="${row.gender}"/></td>
        <td><c:out value="${row.birthDate}"/></td>
    </c:forEach>
        </tr>

</table>












</body>
</html>
