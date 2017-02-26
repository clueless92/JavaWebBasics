<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Todor Ilchev
  Date: 2017-02-19
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Shelves</title>
        <link rel="stylesheet" href="../css/shelves.css">
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <table>
            <thead>
                <th>Title</th>
                <th>Author</th>
                <th>Pages</th>
                <th>Edit</th>
                <th>Delete</th>
            </thead>
            <tbody>
                <c:set var="books" value="${BROWSE_BOOKS_LIST}"/>
                <c:forEach items="${books}" var="book">
                    <tr>
                        <td>${book.title}</td>
                        <td>${book.author}</td>
                        <td>${book.pages}</td>
                        <td><a href="shelves/edit/${book.title}">Edit</a></td>
                        <td><a href="shelves/delete/${book.title}">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
