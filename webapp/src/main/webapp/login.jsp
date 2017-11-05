<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="google-signin-client_id"
          content="372851215939-v2iponke1e57fj4bqagmqsvkkgeu2m9f.apps.googleusercontent.com">
    <script src="https://apis.google.com/js/platform.js?onload=onLoadGoogleCallback" async defer></script>
    <title>ShopAll</title>
    <script>
        window.fbAsyncInit = function () {
            FB.init({
                appId: '${facebookAppId}',
                cookie: true,
                xfbml: true,
                version: 'v2.8'
            });
        };

        function fb_login() {
            FB.login(function (response) {
                if (response.authResponse) {
                    var accessToken = response.authResponse.accessToken;
                    var userId = response.authResponse.userID;

                    FB.api('/me?fields=name,email,picture', function (response) {
                        window.location.href = '/webapp/login?user_name=' + response.name + '&user_email=' + response.email + '&access_token=' + accessToken + '&user_id=' + userId  + '&picture=' + encodeURIComponent(response.picture.data.url);
                    });
                }
            }, {
                scope: 'public_profile,email'
            });
        }

        (function (d, s, id) {
            var js, fjs = d.getElementsByTagName(s)[0];
            if (d.getElementById(id)) return;
            js = d.createElement(s);
            js.id = id;
            js.src = "//connect.facebook.net/en_US/sdk.js";
            fjs.parentNode.insertBefore(js, fjs);
        }(document, 'script', 'facebook-jssdk'));
    </script>
    <script>
        function onLoadGoogleCallback() {
            gapi.load('auth2', function () {
                var auth2 = gapi.auth2.init({
                    client_id: '372851215939-v2iponke1e57fj4bqagmqsvkkgeu2m9f.apps.googleusercontent.com',
                    cookiepolicy: 'single_host_origin',
                    scope: 'profile'
                });

                auth2.attachClickHandler(document.getElementById('google-button'), {},
                    onSignIn, function (error) {
                        console.log('Sign-in error', error);
                    }
                );
            });
        }

        function onSignIn(googleUser) {
            var id_token = googleUser.getAuthResponse().id_token;
            window.location.href = '/webapp/googlelog?id=' + id_token;
        }
    </script>
    <style>
        @import url(http://fonts.googleapis.com/css?family=Open+Sans);

        .login {
            position: absolute;
            top: 50%;
            left: 50%;
            margin: -150px 0 0 -150px;
            width: 300px;
            height: 300px;
            display: flex;
            flex-direction: column;
        }

        .login h1 {
            color: #fff;
            text-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            letter-spacing: 1px;
            text-align: center;
        }

        .loginBtn {
            box-sizing: border-box;
            position: relative;
            margin: 0.2em;
            padding: 0 15px 0 46px;
            border: none;
            text-align: left;
            line-height: 34px;
            white-space: nowrap;
            border-radius: 0.2em;
            font-size: 16px;
            color: #FFF;
            height: 34px;
        }

        .loginBtn:before {
            content: "";
            box-sizing: border-box;
            position: absolute;
            top: 0;
            left: 0;
            width: 34px;
            height: 100%;
        }

        .loginBtn:focus {
            outline: none;
        }

        .loginBtn:active {
            box-shadow: inset 0 0 0 32px rgba(0, 0, 0, 0.1);
        }

        .loginBtn--facebook {
            background-color: #4C69BA;
            background-image: linear-gradient(#4C69BA, #3B55A0);
            text-shadow: 0 -1px 0 #354C8C;
        }

        .loginBtn--facebook:before {
            border-right: #364e92 1px solid;
            background: url('https://s3-us-west-2.amazonaws.com/s.cdpn.io/14082/icon_facebook.png') 6px 6px no-repeat;
        }

        .loginBtn--facebook:hover,
        .loginBtn--facebook:focus {
            background-color: #5B7BD5;
            background-image: linear-gradient(#5B7BD5, #4864B1);
        }

        .loginBtn--google {
            background: #DD4B39;
        }

        .loginBtn--google:before {
            border-right: #BB3F30 1px solid;
            background: url('https://s3-us-west-2.amazonaws.com/s.cdpn.io/14082/icon_google.png') 6px 6px no-repeat;
        }

        .loginBtn--google:hover,
        .loginBtn--google:focus {
            background: #E74B37;
        }
    </style>
    <link rel="stylesheet prefetch" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet prefetch" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.2/css/bootstrap-select.min.css">
    <link rel="stylesheet prefetch" href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/0.8.2/css/flag-icon.min.css">

    <jsp:include page="background.jsp" />
</head>
<body>
<div class="login">
    <h1>ShopAll</h1>
    <button id="facebook-button" class="loginBtn loginBtn--facebook" onclick="fb_login();">
        ${sessionScope['t.fbLogin']}
    </button>
    <button id="google-button" class="loginBtn loginBtn--google">
        ${sessionScope['t.googleLogin']}
    </button>

</div>
 <div id="langpicker" style="position: absolute; right:10px; top:10px">
    <select class="selectpicker" data-width="fit" onchange="window.location.href='?locale='+ $('.selectpicker').val();">
        <option value="" data-content='<span class="flag-icon flag-icon-pl"></span> Polski'></option>
        <option value="en" data-content='<span class="flag-icon flag-icon-us"></span> English'>en</option>
    </select>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.2/js/bootstrap-select.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script>
    $(function(){
        $('select.selectpicker').val('${Locale}');
        $('.selectpicker').selectpicker();
    });
    </script>
    </div>
</body>
</html>