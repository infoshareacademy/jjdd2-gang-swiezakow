<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="pl PL">
<head>
    <jsp:include page="/head.jsp"/>
</head>
<body>
<header>
    <jsp:include page="/header.jsp"/>
</header>
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