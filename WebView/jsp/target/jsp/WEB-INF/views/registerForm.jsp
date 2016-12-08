<%--
  Created by IntelliJ IDEA.
  User: zhaoxl
  Date: 2016/11/25
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<html>
<head>
    <title>Spittr</title>
    <link rel="stylesheet"
            type="text/css"
            href="<c:url value="/resources/style.css"/>">
</head>
<body>
<h1>Register</h1>
<sf:form title="Spring表单" id="springForm" method="post" commandName="spitter">
    FirstName : <sf:input path="firstName"/>
        <sf:errors path="firstName"/><br/>
    LastName : <sf:input path="lastName"/>
    <sf:errors path="lastName"/><br/>
    Email : <sf:input path="email"/>
    <sf:errors path="email"/><br/>
    Password : <sf:password path="password"/>
    <sf:errors path="password"/><br/>
    <input type="submit" value="Register">
</sf:form>
</body>
</html>
