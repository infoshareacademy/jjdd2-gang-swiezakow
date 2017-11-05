<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
<style>
    @import url(http://fonts.googleapis.com/css?family=Open+Sans);

    .image {
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .error {
        margin-top: 10%;
    }
</style>
    <jsp:include page="background.jsp" />
</head>

<body>
<div class="image">
<img class="error" style="width: 500px; height: 200px; object-fit: cover;" src="http://ninaapps.net/Error403.png">
</div>
</body>
</html>