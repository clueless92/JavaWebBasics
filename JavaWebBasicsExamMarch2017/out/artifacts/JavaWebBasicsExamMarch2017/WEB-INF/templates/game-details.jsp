<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Game Store</title>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="menu.jsp"/>

<!--Body of the store-->
<main>
    <div class="container">
        <div class="row">
            <div class="col-12 text-center">

                <h1 class="display-3">${game.title}</h1>
                <br/>

                <iframe width="560" height="315" src="https://www.youtube.com/embed/${game.trailer}" frameborder="0"
                        allowfullscreen></iframe>

                <br/>
                <br/>

                <p>${game.description}</p>

                <p><strong>Price</strong> - ${game.price}&euro;</p>
                <p><strong>Size</strong> - ${game.size} GB</p>
                <p><strong>Release Date</strong> - <fmt:formatDate value="${game.releaseDate}" pattern="dd/MM/yyyy"/>
                </p>

                <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/">Back</a>
                <c:if test="${sessionScope.user != null && sessionScope.user.getRole().name().equals(\"ADMIN\")}">
                    <a class="btn btn-warning"
                       href="${pageContext.request.contextPath}/games/edit/${game.id}">Edit</a>
                    <a class="btn btn-danger"
                       href="${pageContext.request.contextPath}/games/delete/${game.id}">Delete</a>
                </c:if>
                <c:if test="${!sessionScope.user.containsId(game.id)}">
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/game/buy/${game.id}">Buy</a>
                </c:if>
                <br/>
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
