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
<br><br><br>
<h1>Data ostatniej aktualizacji w module raportowym: ${lastUpdateDate}</h1>
<br>

<div class="row">
    <div class="col-4">
        <div class="list-group" id="list-tab" role="tablist">
            <c:forEach items="${tasks}" var="task">
                <a class="list-group-item list-group-item-action" id="list-settings-list" data-toggle="list" href="#${task.id}" role="tab" aria-controls="${task.id}">Zadanie ${task.id}</a>
            </c:forEach>
        </div>
    </div>
    <div class="col-8">
        <div class="tab-content" id="nav-tabContent">
            <c:forEach items="${tasks}" var="task">
                <div class="tab-pane fade" id="${task.id}" role="tabpanel" aria-labelledby="${task.id}">
                    <p>Lista odbiorcow: ${task.emails}</p>
                    <p>Data nastepnego raportu: ${task.sendTimeDate}</p>
                    <p>Ustawiony interwal: ${task.interval} [min]</p>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<p>Nowe zadanie:</p>

<form name="myForm" action="report" method="post" onsubmit="return validateForm();">
    Odbiorca: <input type="text" name="email"><br>
    Data nastepnego raportu: <br>
    Czas: [hh]<input type="text" name="hour">:[mm]<input type="text" name="minutes"><br>
    Data: [dd]<input type="text" name="day">[MM]<input type="text" name="month">[yyyy]<input type="text" name="year"><br>
    Interwal: <input type="text" name="interval"><br>
    <input type="submit" value="Dodaj">
</form>



<form action="report" method="post"></form>
<br>
<p>Usun zadanie:</p>
<form action="delete" method="post" name="deletetasknumber">
    <div class="form-row align-items-center">
        <div class="col-auto">
            <label class="mr-sm-2" for="inlineFormCustomSelect">Wybierz zadanie do usuniecia</label>
            <select class="custom-select mb-2 mr-sm-2 mb-sm-0" id="inlineFormCustomSelect">
                <option selected>Choose...</option>
                <c:forEach items="${tasks}" var="task">
                <option value="${task.id}">${task.id}</option>
                </c:forEach>
            </select>
        </div>
        <div class="col-auto">
            <button type="submit" class="btn btn-primary">Usun to zadanie</button>
        </div>
    </div>
</form>

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