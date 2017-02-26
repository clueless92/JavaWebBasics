<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="bg.tilchev.model.dto.binding.LoginUser" %>
<%@ page import="bg.tilchev.Config" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu.css">
    </head>
    <body>
        <header>
            <nav>
                <ul>
                    <li><a href="/">Home</a></li>
                    <li><a href="/register">Register</a></li>
                    <%
                        LoginUser loginModel = (LoginUser) session.getAttribute(Config.USER_LOGIN);
                        String username = null;
                        if  (loginModel != null) {
                            username = loginModel.getUsername();
                        }
                        request.setAttribute(Config.USERNAME, username);
                    %>
                    <c:set var="username" value="${USERNAME}" scope="session"/>
                    <c:choose>
                        <c:when test="${username != null}">
                            <li><a href="/logout">Logout(${USERNAME})</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="/login">Login</a></li>
                        </c:otherwise>
                    </c:choose>
                    <li><a href="/add">Add Book</a></li>
                    <li><a href="/shelves">Shelves</a></li>
                </ul>
            </nav>
        </header>
    </body>
</html>
