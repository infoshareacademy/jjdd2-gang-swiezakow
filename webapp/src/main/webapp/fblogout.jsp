<!DOCTYPE html>
<html>
<head>
    <title>Facebook Login JavaScript Example</title>
    <meta charset="UTF-8">
</head>
<body>
<script>
    window.fbAsyncInit = function () {
        FB.init({
            appId: '${facebookAppId}',
            cookie: true,
            xfbml: true,
            version: 'v2.8'
        });

        FB.getLoginStatus(function (response) {
            if (response.status === 'connected') {
                FB.logout(function () {
                    document.getElementById("status").innerHTML = '${requestScope['t.fbLogout']}';
                    setTimeout(function(){
                      window.location.href = "main";
                    }, 2000);
                });
            }
        });
    };
    (function (d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) return;
        js = d.createElement(s);
        js.id = id;
        js.src = "//connect.facebook.net/en_US/sdk.js";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));
</script>

<div id="status"></div>

</body>
</html>