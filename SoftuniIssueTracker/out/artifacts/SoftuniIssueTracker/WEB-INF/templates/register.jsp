<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Register</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/action.css"/>
</head>
<body>
<jsp:include page="menu.jsp"/>
<c:forEach var="error" items="${errors}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <strong>Error!</strong> ${error}
    </div>
</c:forEach>
<br/>
<div class="container">
    <div class="row">
        <div class="jumbotron">
            <form method="post">
                <div class="form-group">
                    <input name="username" type="text" class="form-control" placeholder="Enter username">
                </div>
                <div class="form-group">
                    <input name="fullName" type="text" class="form-control" placeholder="Enter full name">
                </div>
                <div class="form-group">
                    <input name="password" type="password" class="form-control" placeholder="Enter password">
                </div>
                <div class="form-group">
                    <input name="confirmPassword" type="password" class="form-control" placeholder="Repeat password">
                </div>
                <div class="form-group">
                    <input class="btn btn-primary" type="submit" value="Register">
                    <a href="${pageContext.request.contextPath}/" class="btn btn-primary">Cancel</a>
                </div>
                <div class="form-group">
                    <a class="pull-right" href="${pageContext.request.contextPath}/login">Already have an account?</a>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script>
    $("#register").addClass("active");
</script>
</body>
</html>
