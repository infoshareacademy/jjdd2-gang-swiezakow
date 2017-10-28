<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="pl PL">
    <head>
        <meta charset="utf-8">
        <meta name="google-signin-client_id" content="372851215939-v2iponke1e57fj4bqagmqsvkkgeu2m9f.apps.googleusercontent.com">
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
        <div class="bg-light">
            <div class="container mt-1">
                <div id="userDetails">Zalogowano jako ${username}
                    <c:choose>
                        <c:when test="${isFbUser}">
                            (<a href="fblogin?logout=1">Wyloguj</a>)
                        </c:when>
                        <c:otherwise>
                            <script src="https://apis.google.com/js/platform.js" async defer></script>
                            <div class="g-signin2" style="display: none;" data-onsuccess="onSignIn"></div>
                            (<a href="#" onclick="signOut();">Wyloguj</a>)
                            <script>
                                function signOut() {
                                    var auth2 = gapi.auth2.getAuthInstance();
                                    auth2.signOut().then(function () {
                                        window.location.href = '/webapp/fblogin?logout=1';
                                    });
                                }
                            </script>
                        </c:otherwise>
                    </c:choose>
                </div>
                ${message}
            </div>

            <div class="container mt-1">
                <div class="row">
                    <div class="col-md-3">
                        <h2>Seria pytań</h2>
                        <p>Wyszukiwanie na podstawie serii pytań.</p>
                    </div>
                    <div class="col-md-3">
                        <h2>Twój produkt</h2>
                        <p>Wyszukiwanie produktu.</p>
                    </div>
                    <div class="col-md-3">
                        <h2>Katalog Allegro</h2>
                        <p>Wyszukiwanie kategorii Allegro.</br></p>
                     </div>
                    <div class="col-md-3">
                        <h2>Asystent Allegro</h2>
                        <p>Pomaga tworzyć zapytania.</br></p>
                     </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <p><a class="btn btn-outline-success" href="SearchByQuestions" role="button">Wyszukaj &raquo;</a></p>
                    </div>
                    <div class="col-md-3">
                        <p><a class="btn btn-outline-success" href="searchCategoryCommand" role="button">Wyszukaj &raquo;</a></p>
                    </div>
                    <div class="col-md-3">
                        <p><a class="btn btn-outline-success" href="categoryPickerCommand" role="button">Wyszukaj &raquo;</a></p>
                    </div>
                    <div class="col-md-3">
                        <p><a class="btn btn-outline-success" href="searchQueryCommand" role="button">Wyszukaj &raquo;</a></p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>