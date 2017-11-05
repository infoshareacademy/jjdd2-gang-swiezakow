<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="google-signin-client_id" content="372851215939-v2iponke1e57fj4bqagmqsvkkgeu2m9f.apps.googleusercontent.com">
<meta name="description" content="">
<meta name="author" content="">
<link href="https://fonts.googleapis.com/css?family=Lato:300,300i" rel="stylesheet">
    <link rel="stylesheet prefetch" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.2/css/bootstrap-select.min.css">
    <link rel="stylesheet prefetch" href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/0.8.2/css/flag-icon.min.css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
      integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<link href="https://getbootstrap.com/docs/4.0/examples/carousel/carousel.css" rel="stylesheet">
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
        integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
        integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
        crossorigin="anonymous"></script>
<script src="https://getbootstrap.com/assets/js/vendor/holder.min.js" crossorigin="anonymous"></script>
<script>
    function signOut() {
        var auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut().then(function () {
            gapi.auth2.getAuthInstance().disconnect();
            setTimeout(function() {
                window.location.href = '/webapp/login?logout=1';
            }, 500);
        });
    }
</script>

<title>ShopAll</title>

<style>

    .profile-avatar {
        border-radius: 50%;
        width: 30px;
        margin-right: 6px;
        flex-grow: 0;
    }

    .profile-section {
        padding-left: 5px;
        font-size: 11px;
    }

    .profile-section > button {
        font-size: 11px;
        color: rgba(255, 255, 255, .5);
        text-decoration: none;
        cursor: pointer;
        padding-right: 0;
        display: flex;
        align-items: center;
    }

    .profile-section > button:hover, .profile-section > button:focus {
        color: rgba(255, 255, 255, .9);
        text-decoration: none;
    }

    body {
        margin: 0;
        padding-top: 0;
    }

    ul.topnav {
        list-style-type: none;
        margin: 0;
        padding: 0;
        overflow: hidden;
        background-color: #333;
    }

    ul.topnav li {
        float: left;
    }

    ul.topnav li a {
        display: block;
        color: white;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
    }

    ul.topnav li a:hover:not(.active) {
        background-color: #111;
    }

    ul.topnav li a.active {
        background-color: #4CAF50;
    }

    ul.topnav li.right {
        float: right;
    }

    @media screen and (max-width: 600px) {
        ul.topnav li.right,
        ul.topnav li {
            float: none;
        }
    }

    .kartka {
        height: 175px;
        margin-bottom: 1%;
        margin-left: 10%;
        margin-right: 10%;
        border: 0;
        background: none !important;
    }

    header {
        height: 64px;
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
        background-size: cover !important;
        background-position-y: center !important;
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
    .dashboard-link, .dashboard-link:hover {
        text-decoration: none;
        color: rgb(90, 90, 90);
    }

</style>