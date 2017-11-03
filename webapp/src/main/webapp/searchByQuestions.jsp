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

<div class="bg-light pt-5">
    <div class="container mt-1">
        ${userDetails}
        ${message}
    </div>
    <div class="container mt-1">
        <c:choose>
            <c:when test="${isResultNotPresent}">
                <div class="container mt-1">
                    <div class="row">
                        <div class="col-md-9">
                            <h2>${requestScope['t.common.seriesOfQuestions']}</h2>
                            <p> ${requestScope['t.searchByQuestions.categoryNotFound']} </p>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:when test="${isLink}">
                <div class="container mt-1">
                    <div class="row">
                        <div class="col-md-9">
                            <h2>${requestScope['t.common.seriesOfQuestions']}</h2>
                            <p> ${requestScope['t.common.AllegroLink']}:
                                <a href="${link}">${link}</a></p>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="container mt-1">
                    <div class="row">
                        <div class="col-md-9">
                            <h2>${requestScope['t.common.seriesOfQuestions']}</h2>
                            <p>${requestScope['t.searchByQuestions.categoryQuestion']} <strong>${categoryName}</strong>?<br/></p>
                            <form method="GET"><input type="hidden" name="categoryId" value="${categoryId}"/>
                                <input class="btn btn-outline-success my-2 my-sm-0" type="submit" name="theAnswer" value="${requestScope['t.searchByQuestions.Yes']}"/>
                                <input class="btn btn-outline-success my-2 my-sm-0" type="submit" name="theAnswer" value="${requestScope['t.searchByQuestions.No']}"/>
                            </form>
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>