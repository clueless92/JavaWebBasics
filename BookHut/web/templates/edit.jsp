<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Edit Book</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <c:set var="book" value="${BOOK}"/>
        <form method="post">
            <label for="title">Title: </label>
            <input id="title" type="text" name="title" value="${book.title}" readonly>
            <label for="author">Author: </label>
            <input id="author" type="text" name="author" value="${book.author}">
            <label for="pages">Pages: </label>
            <input id="pages" type="text" name="pages" value="${book.pages}">
            <input type="submit" name="edit" value="Edit">
        </form>
    </body>
</html>
