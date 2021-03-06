<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
                        <div>
                            <h2>${sessionScope['t.common.seriesOfQuestions']}</h2>
                        </div>
                    </div>
                </div>
                </div>
               <div class="alert alert-warning w-100" role="alert">
                    <p> ${sessionScope['t.searchByQuestions.categoryNotFound']} </p>
               </div>
               <div>
            </c:when>
            <c:when test="${isLink}">
                <div class="container mt-1">
                    <div class="row">
                        <div>
                            <h2>${sessionScope['t.common.seriesOfQuestions']}</h2>
                            <p> ${sessionScope['t.common.AllegroLink']}:
                                <a href="${link}">${link}</a></p>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="container mt-1">
                    <div class="row">
                        <div>
                            <h2>${sessionScope['t.common.seriesOfQuestions']}</h2>
                            <p>${sessionScope['t.searchByQuestions.categoryQuestion']} <strong>${fn:escapeXml(categoryName)}</strong>?<br/></p>
                            <form method="GET"><input type="hidden" name="categoryId" value="${categoryId}"/>
                                <button class="btn btn-outline-success my-2 my-sm-0" type="submit" name="theAnswer" value="Tak">${sessionScope['t.searchByQuestions.Yes']}</button>
                                <button class="btn btn-outline-success my-2 my-sm-0" type="submit" name="theAnswer" value="Nie">${sessionScope['t.searchByQuestions.No']}</button>
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
