<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>Promoted Categories</title>
</head>
<body>
<header>
    <jsp:include page="/header.jsp"/>
</header>
<form action="/webapp/promoted" method="get">
    <jsp:useBean id="categories" scope="request" type="java.util.List<pl.infoshareacademy.AllegroCategory>" />
    <c:forEach items="${categories}" var="category" >
        <input type="checkbox" name="id" value="${category.catID}"
               <c:if test="${selectedIds.contains(category.getCatID())}">checked</c:if>
        >
        <a href="/webapp/promoted?parentid=${category.getCatID()}">${category.name}</a>
        <br/>
    </c:forEach>
<input type="submit" name="save" value="${sessionScope['t.promotedCategoriesSave']}">
<input type="hidden" name="parentid" value="${actualId}">
</form>
</body>
</html>