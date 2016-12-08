<%--
  Created by IntelliJ IDEA.
  User: zhaoxl
  Date: 2016/11/25
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
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
<h1>Recent Spittles</h1>
        <div class="spittleMessage">
            <c:out value="${spittle.message}"/>
        </div>
        <div>
            <span class="spittleTime"><c:out value="${spittle.time}" /> </span>
        </div>
</body>
</html>
