<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Home</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css"/>
</head>
<body>
<jsp:include page="menu.jsp"/>
<div class="container">
    <h1 id="welcome" class="h1">Welcome to Issue Tracker</h1>
</div>
<script  src="${pageContext.request.contextPath}/jquery/jquery.min.js"></script>
<script  src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script>
    $("#home").addClass("active");
</script>
</body>
</html>
