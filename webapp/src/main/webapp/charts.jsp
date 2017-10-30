<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <jsp:include page="/head.jsp"/>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.0/Chart.bundle.min.js"></script>
    <style>
        .charts {
            display: flex;
        }
        .chart {
            width: 400px;
            height: 400px;
        }
        .chart canvas {
            width: 400px;
            height: 400px;
        }
    </style>
</head>
<body>
<header>
    <jsp:include page="/header.jsp"/>
</header>
    <div class="charts">
        <div class="chart">
            <canvas id="chart"></canvas>
        </div>
        <div class="chart">
            <canvas id="chart2"></canvas>
        </div>
        <div class="chart">
            <canvas id="chart3"></canvas>
        </div>
        <div class="chart">
            <canvas id="chart5"></canvas>
        </div>
    </div>
    <div>
        <canvas id="chart4"></canvas>
    </div>
    <script>

        var data = ${json};
        var data2 = ${json2};
        var data3 = ${json3};
        var data4 = ${json4};
        var data5 = ${json5};

        var ctx = document.getElementById("chart");
        var options = {
            width: 400,
            height: 400
        };
        new Chart(ctx,{
            type: 'doughnut',
            data: data,
            options: options
        });

        var ctx2 = document.getElementById("chart2");
        var options2 = {
            width: 400,
            height: 400
        };
        new Chart(ctx2,{
            type: 'doughnut',
            data: data2,
            options: options2
        });

        var ctx3 = document.getElementById("chart3");
        var options3 = {
            width: 400,
            height: 400
        };
        new Chart(ctx3,{
            type: 'doughnut',
            data: data3,
            options: options3
        });

        var ctx4 = document.getElementById("chart4");
        var options4 = {};
        new Chart(ctx4, {
            type: 'line',
            data: data4,
            options: options4
        });

        var ctx5 = document.getElementById("chart5");
        var options5 = {
            width: 400,
            height: 400
        };
        new Chart(ctx5,{
            type: 'doughnut',
            data: data5,
            options: options5
        });
    </script>
</body>
</html>