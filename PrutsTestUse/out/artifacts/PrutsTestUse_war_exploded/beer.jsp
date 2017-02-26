<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Beer</title>
    </head>
    <body>
        <p>I am a beer template</p>
        <form method="post">
            <input type="text" name="beerBrand" placeholder="Enter a Beer Brand"/>
            <input type="submit" value="Submit"/>
        </form>
        <p>I am coming from a model: ${key}</p>
    </body>
</html>
