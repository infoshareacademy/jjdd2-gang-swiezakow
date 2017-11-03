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
<nav class="navbar navbar-light bg-light">
    <form class="formularz form-inline" method="get">
        <input
                name="searchTerm"
                class="szukaj form-control mr-sm-2"
                type="text"
                placeholder="${requestScope['t.searchCategoryCommandPrompt']}"
                aria-label="Search"
                value="${searchTerm}"
        >
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">${requestScope['t.searchCategoryCommandSearch']}</button>
    </form>
</nav>
<c:if test="${searchTerm.length() != 0 && searchTerm.length() < 3}">
    <div class="alert alert-danger" role="alert">
        ${requestScope['t.searchCategoryCommandMinLengthInfo']}
    </div>
</c:if>
<c:if test="${searchTerm.length() >= 3 && queryCard.size() == 0}">
    <div class="alert alert-warning" role="alert">
        ${requestScope['t.searchCategoryCommandCategoryNotFound']}
    </div>
</c:if>
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
                <label class="form-control-label" for="formGroupExampleInput">${requestScope['t.searchQueryCommandToSearchCopy']}:</label>
                <input type="text" class="form-control" id="formGroupExampleInput" value="${qc.phrase}">
            </div>
        </div>
    </div>
</c:forEach>
</body>
</html>