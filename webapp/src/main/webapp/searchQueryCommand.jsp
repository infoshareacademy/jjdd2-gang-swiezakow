<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="pl PL">
<head>
    <jsp:include page="/head.jsp"/>
</head>
<body>
<ul class="topnav">
    <li><a href="SearchByQuestions">Wyszukiwanie na podstawie serii pytań</a></li>
    <li><a href="searchCategoryCommand">Wyszukiwanie produktu</a></li>
    <li><a href="categoryPickerCommand">Wyszukiwanie kategorii Allegro</a></li>
    <li><a class="active" href="searchQueryCommand">Asysent Allegro</a></li>
    <li class="right"><a href="stats">Statystyki</a></li>
    <li class="right"><a href="">O nas</a></li>
</ul>
<nav class="navbar navbar-light bg-light">
    <form class="formularz form-inline" method="get">
        <input
                name="searchTerm"
                class="szukaj form-control mr-sm-2"
                type="text"
                placeholder="Czego szukasz na allegro?"
                aria-label="Search"
                value="${searchTerm}"
        >
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
</nav>

<jsp:useBean id="queryCard" scope="request" type="java.util.List<pl.infoshareacademy.webapp.searchQueryCommandWeb.QueryCard>" />
<c:forEach items="${queryCard}" var="qc">
    <div class="kartka card bg-dark text-white">
        <div class="card-img-overlay" style="
                background: url(${qc.backgroundUrl});
                <c:if test="${qc.promoted}">border: 5px chartreuse solid;</c:if>"
        >
            <h4 class="card-title">
                ${qc.categoryName}
            </h4>
            <div class="form-group" style="margin-top: 30px">
                <label class="form-control-label" for="formGroupExampleInput">Aby wyszukać skopiuj:</label>
                <input type="text" class="form-control" id="formGroupExampleInput" value="${qc.phrase}">
            </div>
        </div>
    </div>
</c:forEach>
</body>
</html>