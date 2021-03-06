<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="pl PL">
<head>
    <jsp:include page="/head.jsp"/>

<style>

* {
font-family: 'Lato', sans-serif;
}

</style>
</head>
<body>
<header>
    <jsp:include page="/header.jsp"/>
</header>
<jsp:useBean id="mainCategories" scope="request" type="java.util.List<pl.infoshareacademy.webapp.categoryPickerCommandWeb.PickerCommandCard>" />
<div class="container">
    <div class="row justify-content-md-center">
        <div class="col-6 col-md-4">
            <c:forEach items="${mainCategories}" var="category" step="3" begin="0">
                <div class="kartka card bg-dark text-white">
                    <div class="card-img-overlay" style="background: url(${category.backgroundURL});
                            background-size: cover;
                            background-position-y: center;
                            border-radius: 20px;
                    <c:if test="${category.promoted}">border: 5px chartreuse solid;</c:if>
                            box-shadow: 2px 3px 10px #333;">
                        <a style="color: white; text-shadow: 2px 2px 2px #333333" href="${category.childrenLink}">
                            <h4 class="card-title" style="text-shadow: 2px 2px 2px #333333; margin-bottom: 60px; background: rgba(0, 0, 0, 0.6); font-size: 2.0em; padding: 1%; font-weight: 200; text-align: center">${fn:escapeXml(category.categoryName)}</h4>
                        </a>
                        <a style="color: white; text-shadow: 2px 2px 2px #333333" href="${category.allegroLink}">${sessionScope['t.common.AllegroLink']}</a>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="col-6 col-md-4">
            <c:forEach items="${mainCategories}" var="category" step="3" begin="1" varStatus="mainCategories">
                <div class="kartka card bg-dark text-white">
                    <div class="card-img-overlay" style="background: url(${category.backgroundURL});
                            background-size: cover;
                            background-position-y: center;
                            border-radius: 20px;
                    <c:if test="${category.promoted}">border: 5px chartreuse solid;</c:if>
                            box-shadow: 2px 3px 10px #333;">
                        <a style="color: white; text-shadow: 2px 2px 2px #333333" href="${category.childrenLink}">
                            <h4 class="card-title" style="text-shadow: 2px 2px 2px #333333; margin-bottom: 60px; background: rgba(0, 0, 0, 0.6); font-size: 2.0em; padding: 1%; font-weight: 200; text-align: center">${fn:escapeXml(category.categoryName)}</h4>
                        </a>
                        <a style="color: white; text-shadow: 2px 2px 2px #333333" href="${category.allegroLink}">${sessionScope['t.common.AllegroLink']}</a>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="col-6 col-md-4">
            <c:forEach items="${mainCategories}" var="category" step="3" begin="2" varStatus="mainCategories">
                <div class="kartka card bg-dark text-white">
                    <div class="card-img-overlay" style="background: url(${category.backgroundURL});
                            background-size: cover;
                            background-position-y: center;
                            border-radius: 20px;
                    <c:if test="${category.promoted}">border: 5px chartreuse solid;</c:if>
                            box-shadow: 2px 3px 10px #333;">
                        <a style="color: white; text-shadow: 2px 2px 2px #333333" href="${category.childrenLink}">
                            <h4 class="card-title" style="text-shadow: 2px 2px 2px #333333; margin-bottom: 60px; background: rgba(0, 0, 0, 0.6); font-size: 2.0em; padding: 1%; font-weight: 200; text-align: center">${fn:escapeXml(category.categoryName)}</h4>
                        </a>
                        <a style="color: white; text-shadow: 2px 2px 2px #333333" href="${category.allegroLink}">${sessionScope['t.common.AllegroLink']}</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>