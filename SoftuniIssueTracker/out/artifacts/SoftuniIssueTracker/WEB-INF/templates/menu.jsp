<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Menu</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu.css"/>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">

        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">Issue Tracker</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li id="home"><a href="${pageContext.request.contextPath}/">Home <span class="sr-only">(current)</span></a></li>
                <li id="issues"><a href="${pageContext.request.contextPath}/issues">Issues</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <c:choose >
                    <c:when test="${null == user}">
                        <li id="register"><a href="${pageContext.request.contextPath}/register">Register</a></li>
                        <li id="login"><a href="${pageContext.request.contextPath}/login">Login</a></li>
                    </c:when>
                    <c:otherwise>
                        <li id="login"><a href="${pageContext.request.contextPath}/logout">Logout(${user.username})</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>
