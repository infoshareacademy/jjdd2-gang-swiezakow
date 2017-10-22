<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="pl PL">
<head>
    <meta charset="utf-8">
    <link href="https://fonts.googleapis.com/css?family=Lato:300,300i" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
            integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
            integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
            crossorigin="anonymous"></script>
    <style>
        * {
            font-family: 'Lato', sans-serif;
        }
    </style>
</head>
<body>
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
                                 <a href="${link}">${link}</a></p>
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
</body>
</html>