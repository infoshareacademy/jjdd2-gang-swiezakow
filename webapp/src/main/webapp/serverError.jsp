<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Kalam" rel="stylesheet">
    <title>Server Error</title>
    <jsp:include page="/head.jsp"/>
    <jsp:include page="background.jsp"/>
    <style>
        .status-code {
            color: wheat;
            font-family: Kalam;
            text-align: center;
            margin-top: 180px;
            font-size: 100px;
        }

        .description {
            color: wheat;
            font-family: Kalam;
            text-align: center;
            margin-top: 5px;
            font-size: 50px;
        }
    </style>
</head>
<body>
<header>
    <jsp:include page="/header.jsp"/>
</header>
<div class="status-code">500</div>
<div class="description">Przepraszamy! Coś poszło nie tak.</div>
<div class="description">${exceptionText}</div>
</body>
</html>