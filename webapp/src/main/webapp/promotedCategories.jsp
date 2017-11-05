<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="pl">
<head>
    <jsp:include page="/head.jsp"/>
    <jsp:include page="/background.jsp"/>
    <style>
        form {
            box-shadow: 15px 15px 100px rgba(0, 0, 0, .5);
            width: 500px;
            background: rgba(255,255,255,0.1);
            margin-left: auto;
            margin-right: auto;
            border: 1px solid #ccc;
            padding: 20px;
            border-radius: 15px;
            margin-top: 40px;
        }

        .promoted-link {
            color: whitesmoke;
            font-size: 1.15rem;
        }

        body {
            overflow: auto;
        }
    </style>
</head>
<body>
<header>
    <jsp:include page="/header.jsp"/>
</header>
<form action="/webapp/promoted" method="get">
    <jsp:useBean id="categories" scope="request" type="java.util.List<pl.infoshareacademy.AllegroCategory>" />
    <c:forEach items="${categories}" var="category" >
    <div class="form-group">
        <label class="custom-control custom-checkbox">
            <input type="checkbox" class="custom-control-input" name="id" value="${category.catID}"
                   <c:if test="${selectedIds.contains(category.getCatID())}">checked</c:if>
            >
            <span class="custom-control-indicator"></span>
            <span class="custom-control-description">
                <a class="promoted-link" href="/webapp/promoted?parentid=${category.getCatID()}">${fn:escapeXml(category.name)}</a>
            </span>
        </label>
    </div>
    </c:forEach>

    <button type="submit" class="btn btn-primary" name="save" value="save">${sessionScope['t.promotedCategoriesSave']}</button>
<input type="hidden" name="parentid" value="${actualId}">
</form>
</body>
</html>