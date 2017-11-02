<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Report</title>
    <jsp:include page="/head.jsp"/>
</head>
<body>
<style>
    * {
        font-family: 'Lato', sans-serif;
    }
</style>

<jsp:include page="/header.jsp"/>

<h1>Data ostatniej aktualizacji w module raportowym: ${lastUpdateDate}</h1>

<c:forEach items="${tasks}" var="task">
    <p>Identyfikator zadania: ${task.id}</p>
    <p>Lista odbiorcow: ${task.emails}</p>
    <p>Data nastepnego raportu: ${task.sendTimeDate}</p>
    <p>Ustawiony interwal: ${task.interval} [min]</p>
        <br>
</c:forEach>

<p>Nowe zadanie:</p>

<form name="myForm" action="report" method="post" onsubmit="return validateForm();">
    Odbiorca: <input type="text" name="email"><br>
    Data nastepnego raportu: <br>
    Czas: [mm]<input type="text" name="minutes">:
    [hh]<input type="text" name="hour"><br>
    [dd]<input type="text" name="day">
    [MM]<input type="text" name="month">
    [yyyy]<input type="text" name="year"><br>
    Interwal: <input type="text" name="interval"><br>
    <input type="submit" value="Dodaj">
</form>

<form action="report" method="post"></form>

<script>
    function validateForm() {
        var x = document.forms["myForm"]["email"].value;
        var atpos = x.indexOf("@");
        var dotpos = x.lastIndexOf(".");
        if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) {
            alert("Not a valid e-mail address");
            return false;
        }
    }
</script>
</body>
</html>
