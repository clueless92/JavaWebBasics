<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Edit</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/action.css"/>
</head>
<body>
<jsp:include page="menu.jsp"/>
<br/>
<div class="container">
    <div class="row">
        <div class="jumbotron">
            <form method="post">
                <div class="form-group">
                    <input name="name" type="text" class="form-control" placeholder="Enter issue name" value="${issue.name}">
                </div>
                <div class="form-group">
                    <select name="status" class="form-control" required>
                        <optgroup label="Status">
                            <option hidden selected>${issue.status}</option>
                            <option>NEW</option>
                            <option>SOLVED</option>
                        </optgroup>
                    </select>
                </div>
                <div class="form-group">
                    <select name="priority" class="form-control" required>
                        <optgroup label="Priority">
                            <option hidden selected>${issue.priority}</option>
                            <option>LOW</option>
                            <option>MEDIUM</option>
                            <option>HIGH</option>
                        </optgroup>
                    </select>
                </div>
                <div class="form-group">
                    <input class="btn btn-primary" type="submit" value="Edit">
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
