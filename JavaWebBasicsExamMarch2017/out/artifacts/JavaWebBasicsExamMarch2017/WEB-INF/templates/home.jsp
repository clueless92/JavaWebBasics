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
<!--Body of the store-->
<main>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="text-center"><h1 class="display-3">SoftUni Store</h1></div>

                <form class="form-inline">
                    Filter:
                    <input type="submit" name="filter" class="btn btn-link" value="All"/>
                    <c:if test="${user != null}">
                        <input type="submit" name="filter" class="btn btn-link" value="Owned"/>
                    </c:if>
                </form>

                <div class="card-group">
                    <c:set var="count" scope="page" value="${1}"/>
                    <c:forEach var="game" items="${games}">
                    <div class="card col-4 thumbnail">

                        <img
                                class="card-image-top img-fluid img-thumbnail"
                                onerror="this.src='https://i.ytimg.com/vi/${game.trailer}/maxresdefault.jpg';"
                                src="${game.thumbnail}">

                        <div class="card-block">
                            <h4 class="card-title">${game.title}</h4>
                            <p class="card-text"><strong>Price</strong> - ${game.price}&euro;</p>
                            <p class="card-text"><strong>Size</strong> - ${game.size} GB</p>
                            <p class="card-text">${game.description}</p>
                        </div>

                        <div class="card-footer">
                            <c:if test="${sessionScope.user != null && sessionScope.user.getRole().name().equals(\"ADMIN\")}">
                                <a class="card-button btn btn-warning" name="edit"
                                   href="${pageContext.request.contextPath}/games/edit/${game.id}">Edit</a>
                                <a class="card-button btn btn-danger" name="delete"
                                   href="${pageContext.request.contextPath}/games/delete/${game.id}">Delete</a>
                            </c:if>
                            <a class="card-button btn btn-outline-primary" name="info"
                               href="${pageContext.request.contextPath}/game/info/${game.id}">Info</a>
                            <c:if test="${!sessionScope.user.containsId(game.id)}">
                                <a class="card-button btn btn-primary" name="buy"
                                   href="${pageContext.request.contextPath}/game/buy/${game.id}">Buy</a>
                            </c:if>
                        </div>

                    </div>
                    <c:if test="${count % 3 == 0}">
                </div>
                <div class="card-group">
                    </c:if>
                    <c:set var="count" value="${count + 1}"/>
                    </c:forEach>
                </div>
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
