<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta charset="utf-8">
<link href="https://fonts.googleapis.com/css?family=Lato:300,300i" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
<style>

    body {margin: 0;}

    ul.topnav {
        list-style-type: none;
        margin: 0;
        padding: 0;
        overflow: hidden;
        background-color: #333;
    }

    ul.topnav li {float: left;}

    ul.topnav li a {
        display: block;
        color: white;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
    }

    ul.topnav li a:hover:not(.active) {background-color: #111;}

    ul.topnav li a.active {background-color: #4CAF50;}

    ul.topnav li.right {float: right;}

    @media screen and (max-width: 600px){
        ul.topnav li.right,
        ul.topnav li {float: none;}
    }

    .kartka {
        height: 175px;
        margin-bottom: 1%;
        margin-left: 10%;
        margin-right: 10%;
        border: 0;
        background: none!important;
    }

    * {
        font-family: 'Lato', sans-serif;
    }

    .formularz {
        width: 100%;
        margin-left: 10%;
        margin-right: 10%;
        flex-wrap: nowrap;
    }

    .szukaj {
        flex-grow: 1;
    }
    .card-img-overlay {
        background-size: cover!important;
        background-position-y: center!important;
        border-radius: 20px;
        box-shadow: 2px 3px 10px #333;
    }
    .card-title {
        text-shadow: 2px 2px 2px #333333;
        background: rgba(0, 0, 0, 0.6);
        font-size: 1.5em;
        padding: 1%;
        font-weight: 200;
        text-align: center
    }
    .form-control-label {
        text-shadow: 1px 1px 1px #333333;
        font-size: 0.85em
    }

    .category-link {
        color: white;
        text-shadow: 2px 2px 2px #333333;
    }
</style>