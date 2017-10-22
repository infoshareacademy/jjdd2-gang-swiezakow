<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="pl PL">
<head>
    <jsp:include page="/head.jsp"/>
</head>
<body>
<jsp:useBean id="mainCategories" scope="request" type="java.util.List<pl.infoshareacademy.webapp.categoryPickerCommandWeb.PickerCommandCard>" />
<c:forEach items="${mainCategories}" var="category">
    <div class="kartka card bg-dark text-white">
        <div class="card-img-overlay" style="background: url(${category.backgroundURL});
    background-size: cover;
    background-position-y: center;
    border-radius: 20px;
    <c:if test="${category.promoted}">border: 5px chartreuse solid;</c:if>
    box-shadow: 2px 3px 10px #333;">
            <a style="color: white; text-shadow: 2px 2px 2px #333333" href="${category.childrenLink}">
                <h4 class="card-title" style="text-shadow: 2px 2px 2px #333333; margin-bottom: 60px; background: rgba(255, 255, 255, 0.4); font-size: 2.0em; padding: 1%; font-weight: 200; text-align: center">${category.categoryName}</h4>
            </a>
            <a style="color: white; text-shadow: 2px 2px 2px #333333" href="${category.allegroLink}">link do strony Allegro</a>
        </div>
    </div>
</c:forEach>
</body>
</html>