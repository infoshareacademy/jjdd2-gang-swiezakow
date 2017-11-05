<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/head.jsp"/>
    <jsp:include page="background.jsp"/>
</head>
<body>
<header>
    <jsp:include page="/header.jsp"/>
</header>
<form action="index" method="post" enctype="multipart/form-data">
    <p>
        Wybierz plik <input type="file" name="fileXML"/>
    </p>
    <button type="submit">Send</button>
</form>
</body>
</html>
