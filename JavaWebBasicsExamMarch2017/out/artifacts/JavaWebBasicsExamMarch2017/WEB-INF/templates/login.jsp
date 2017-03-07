<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Game Store</title>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="menu.jsp"/>
<c:forEach var="error" items="${errors}">
    <div class="alert alert-danger" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
        <strong>Oh snap!</strong> ${error}
    </div>
</c:forEach>

<main class="col-4 offset-4 text-center">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="text-center"><h1 class="display-3">Login</h1></div>
                <br/>
                <form method="post">
                    <div class="form-group row">
                        <label class="sr-only">Email</label>
                        <input type="text" name="email" class="form-control" placeholder="somebody@example.com"/>
                    </div>
                    <div class="form-group row">
                        <label class="sr-only">Password</label>
                        <input type="password" name="password" class="form-control" placeholder="Password"/>
                    </div>

                    <input type="submit" class="btn btn-outline-warning btn-lg btn-block"
                           value="Login"/>
                </form>
                <br/>
            </div>
        </div>
    </div>
</main>
<br/>

<!--Footer-->
<footer>
    <div class="container modal-footer">
        <p>&copy; 2017 - Software University Foundation</p>
    </div>
</footer>

<script src="${pageContext.request.contextPath}/scripts/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
