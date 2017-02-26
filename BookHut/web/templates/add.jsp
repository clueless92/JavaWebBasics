<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add Book</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <form method="post">
            <label for="title">Title: </label>
            <input type="text" name="title" id="title">
            <label for="author">Author: </label>
            <input type="text" name="author" id="author">
            <label for="pages">Pages: </label>
            <input type="text" name="pages" id="pages">
            <input type="submit" name="add" value="Add">
        </form>
    </body>
</html>
