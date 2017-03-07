<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add</title>
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
                    <input name="name" type="text" class="form-control" placeholder="Enter issue name">
                </div>
                <div class="form-group">
                    <select name="status" class="form-control" required>
                        <optgroup label="Status">
                            <option disabled hidden selected>Status</option>
                            <option>NEW</option>
                            <option>SOLVED</option>
                        </optgroup>
                    </select>
                </div>
                <div class="form-group">
                    <select name="priority" class="form-control" required>
                        <optgroup label="Priority">
                            <option disabled hidden selected>Priority</option>
                            <option>LOW</option>
                            <option>MEDIUM</option>
                            <option>HIGH</option>
                        </optgroup>
                    </select>
                </div>
                <div class="form-group">
                    <input class="btn btn-success" type="submit" value="Add">
                    <a href="${pageContext.request.contextPath}/issues" class="btn btn-primary">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</div>

<script  src="${pageContext.request.contextPath}/jquery/jquery.min.js"></script>
<script  src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script>
    $("#issue").addClass("active");
</script>
</body>
</html>
