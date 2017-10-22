<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="pl PL">
<head>
    <meta charset="utf-8">
    <link href="https://fonts.googleapis.com/css?family=Lato:300,300i" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
            integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
            integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
            crossorigin="anonymous"></script>
    <style>
        * {
            font-family: 'Lato', sans-serif;
        }
    </style>
</head>
<body>
<div class="container mt-1">
    <c:choose>
        <c:when test="${isResultNotPresent}">
            <p> Niestety nie udało się znaleźć interesującej Cię kategorii </p>
            <p><a href="javascript:history.back()">Poprzednia kategoria</a></p>
            <p><a href="SearchByQuestions">Powrót do pierwszej kategorii</a></p>
            <p><a href="main">Strona główna</a></p>
        </c:when>
        <c:when test="${isLink}">
            <p> Link do kategorii:
                <a href="${link}">${link}</a></p>
            <p><a href="javascript:history.back()">Poprzednia kategoria</a></p>
            <p><a href="SearchByQuestions">Powrót do pierwszej kategorii</a></p>
            <p><a href="main">Strona główna</a></p>
        </c:when>
        <c:otherwise>
            <p>Czy jesteś zainteresowany produktami z kategorii ${categoryName}?<br/></p>
            <form method="GET"><input type="hidden" name="categoryId" value="${categoryId}"/>
                <input class="btn btn-outline-success my-2 my-sm-0" type="submit" name="theAnswer" value="Tak"/>
                <input class="btn btn-outline-success my-2 my-sm-0" type="submit" name="theAnswer" value="Nie"/>
            </form>
            <p><a href="javascript:history.back()">Poprzednia kategoria</a></p>
            <p><a href="SearchByQuestions">Powrót do pierwszej kategorii</a></p>
            <p><a href="main">Strona główna</a></p>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>