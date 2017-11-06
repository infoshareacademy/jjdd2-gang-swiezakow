<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/head.jsp"/>
    <jsp:include page="background.jsp"/>
    <style>
        form {
            box-shadow: 15px 15px 100px rgba(0, 0, 0, .5);
            width: 500px;
            background: rgba(255, 255, 255, 0.1);
            margin-left: auto;
            margin-right: auto;
            border: 1px solid #ccc;
            padding: 20px;
            border-radius: 15px;
            margin-top: 40px;
        }

        .form-group {
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
<form action="index" method="post" enctype="multipart/form-data">
    <div class="form-group">
        <label for="file2">Wybierz plik</label>
        <input type="file" class="form-control-file" id="file2" name="fileXML">
    </div>
    <button class="btn btn-primary" type="submit">Send</button>
</form>
</body>
</html>
