<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu</title>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<c:choose>
    <c:when test="${sessionScope.user == null}">
        <jsp:include page="menus/menu-guest.jsp"/>
    </c:when>
    <c:when test="${sessionScope.user.getRole().name().equals(\"ADMIN\")}">
        <jsp:include page="menus/menu-admin.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="menus/menu-user.jsp"/>
    </c:otherwise>
</c:choose>
</body>
</html>
