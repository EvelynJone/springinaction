<%--
  Created by IntelliJ IDEA.
  User: zhaoxl
  Date: 2016/11/25
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
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
<c:forEach items="${spittleList}" var="spittle">
    <li id="spittle_<c:out value="spittle.id"/> ">
        <div class="spittleMessage">
            <c:out value="${spittle.message}"/>
        </div>
        <div>
            <span class="spittleTime"><c:out value="${spittle.time}" /> </span>
            <span class="spittleLocation">
                (<c:out value="${spittle.latitude}"/> ,
                <c:out value="${spittle.longitude}"/>)</span>
        </div>
    </li>
</c:forEach>

</body>
</html>
