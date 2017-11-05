<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                placeholder="${sessionScope['t.searchCategoryCommandPrompt']}"
                aria-label="Search"
                value="${fn:escapeXml(searchTerm)}"
        >
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">${sessionScope['t.searchCategoryCommandSearch']}</button>
    </form>
</nav>
<c:if test="${searchTerm.length() != 0 && searchTerm.length() < 3}">
    <div class="alert alert-danger" role="alert">
        ${sessionScope['t.searchCategoryCommandMinLengthInfo']}
    </div>
</c:if>
<c:if test="${searchTerm.length() >= 3 && searchResults.size() == 0}">
    <div class="alert alert-warning" role="alert">
        ${sessionScope['t.searchCategoryCommandCategoryNotFound']}
    </div>
</c:if>
<jsp:useBean id="searchResults" scope="request"
             type="java.util.List<pl.infoshareacademy.webapp.searchCategoryCommandWeb.SearchResult>"/>
<c:forEach items="${searchResults}" var="result">
    <div class="kartka card bg-dark text-white">
        <div class="card-img-overlay" style="
                background: url(${result.backgroundImageUrl}); <c:if test="${result.promoted}">border: 5px chartreuse solid;</c:if>
            ">
            <h4 class="card-title">
                <c:forEach items="${result.parentLinks}" var="parentLink">
                    <a class="category-link" href="${parentLink.link}">${fn:escapeXml(parentLink.name)}</a> -
                </c:forEach>
                <a class="category-link" href="${result.categoryLink}">${fn:escapeXml(result.category.name)}</a>
            </h4>
        </div>
    </div>
</c:forEach>
</body>
</html>