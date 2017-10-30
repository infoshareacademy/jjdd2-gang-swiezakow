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
                        <div class="col-md-3 " style="background-color:#e6ffe6" style="border-radius: 10px">
                            <div>
                                </br>
                                <p>Strona główna </br>
                                    <a class="btn btn-outline-success my-2 my-sm-0" href="main">Przejdź</a></br></br></p>
                            </div>
                            <div>
                                <p>Poprzednia kategoria </br>
                                    <a class="btn btn-outline-success my-2 my-sm-0" href="javascript:history.back()">Przejdź</a></br></br></p>
                            </div>
                            <div>
                                <p>Powrót do pierwszej kategorii </br>
                                    <a class="btn btn-outline-success my-2 my-sm-0" href="SearchByQuestions">Przejdź</a></br></br></p>
                            </div>
                        </div>
                        <div class="col-md-9">
                            <h2>Seria pytań</h2>
                            <p> Niestety nie udało się znaleźć interesującej Cię kategorii </p>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:when test="${isLink}">
                <div class="container mt-1">
                    <div class="row">
                        <div class="col-md-3 " style="background-color:#e6ffe6" style="border-radius: 10px">
                            <div>
                                </br>
                                <p>Strona główna </br>
                                    <a class="btn btn-outline-success my-2 my-sm-0" href="main">Przejdź</a></br></br></p>
                            </div>
                            <div>
                                <p>Poprzednia kategoria </br>
                                    <a class="btn btn-outline-success my-2 my-sm-0" href="javascript:history.back()">Przejdź</a></br></br></p>
                            </div>
                            <div>
                                <p>Powrót do pierwszej kategorii </br>
                                    <a class="btn btn-outline-success my-2 my-sm-0" href="SearchByQuestions">Przejdź</a></br></br></p>
                            </div>
                        </div>
                        <div class="col-md-9">
                            <h2>Seria pytań</h2>
                            <p> Link do kategorii:
                                <a href="${secretLink}">${link}</a></p>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="container mt-1">
                    <div class="row">
                        <div class="col-md-3 " style="background-color:#e6ffe6" style="border-radius: 10px">
                            <div>
                                </br>
                                <p>Strona główna </br>
                                    <a class="btn btn-outline-success my-2 my-sm-0" href="main">Przejdź</a></br></br></p>
                            </div>
                            <div>
                                <p>Poprzednia kategoria </br>
                                    <a class="btn btn-outline-success my-2 my-sm-0" href="javascript:history.back()">Przejdź</a></br></br></p>
                            </div>
                            <div>
                                <p>Powrót do pierwszej kategorii </br>
                                    <a class="btn btn-outline-success my-2 my-sm-0" href="SearchByQuestions">Przejdź</a></br></br></p>
                            </div>
                        </div>
                        <div class="col-md-9">
                            <h2>Seria pytań</h2>
                            <p>Czy jesteś zainteresowany produktami z kategorii <strong>${categoryName}</strong>?<br/></p>
                            <form method="GET"><input type="hidden" name="categoryId" value="${categoryId}"/>
                                <input class="btn btn-outline-success my-2 my-sm-0" type="submit" name="theAnswer" value="Tak"/>
                                <input class="btn btn-outline-success my-2 my-sm-0" type="submit" name="theAnswer" value="Nie"/>
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