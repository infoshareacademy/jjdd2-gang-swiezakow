<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <jsp:include page="/head.jsp"/>
    <style>
        .shopping-caption {
            text-shadow: 2px 2px 2px #333333;
            right: 0;
            left: initial;
            padding-right: 40px;
            padding-left: 40px;
            background-color: rgba(0, 0, 0, .5);
        }
        .carousel-item > img {
            object-fit: cover;
        }
    </style>
</head>
<body>
<header>
    <jsp:include page="/header.jsp"/>
</header>

<main role="main">

    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1" class=""></li>
            <li data-target="#myCarousel" data-slide-to="2" class=""></li>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img class="first-slide"
                     src="${image.getUrl()}"
                     alt="First slide">
                <div class="container">
                    <div class="shopping-caption carousel-caption text-right">
                        <h1>${image.getName()}</h1>
                        <p><a class="btn btn-lg btn-primary" href="${image.getAllegroLink()}" target="_blank" role="button">${requestScope['t.mainMenuPrompt']}</a></p>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <img class="second-slide"
                     src="${image2.getUrl()}"
                     alt="Second slide">
                <div class="container">
                    <div class="shopping-caption carousel-caption text-right">
                        <h1>${image2.getName()}</h1>
                        <p><a class="btn btn-lg btn-primary" href="${image2.getAllegroLink()}" target="_blank" role="button">${requestScope['t.mainMenuPrompt']}</a></p>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <img class="third-slide"
                     src="${image3.getUrl()}"
                     alt="Third slide">
                <div class="container">
                    <div class="shopping-caption carousel-caption text-right">
                        <h1>${image3.getName()}</h1>
                        <p><a class="btn btn-lg btn-primary" href="${image3.getAllegroLink()}" target="_blank" role="button">${requestScope['t.mainMenuPrompt']}</a></p>
                    </div>
                </div>
            </div>
        </div>
        <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>

    <div class="container marketing">

        <div class="row">
            <div class="col-lg-4">
                <img class="rounded-circle"
                     src="https://i.kinja-img.com/gawker-media/image/upload/s--f2mYtrrv--/c_scale,fl_progressive,q_80,w_800/1333998000191946087.jpg"
                     alt="Generic placeholder image" width="140" height="140">
                <h2>Bruce Wayne</h2>
                <p>Korzystam z ShopAll od 3 lat. Rodzicie byliby ze mnie dumni.</p>
            </div>
            <div class="col-lg-4">
                <img class="rounded-circle"
                     src="http://i0.kym-cdn.com/photos/images/facebook/000/177/888/mfw.jpg"
                     alt="Generic placeholder image" width="140" height="140">
                <h2>Peter Parker</h2>
                <p>Wujek polecił mi tę stronę</p>
            </div>
            <div class="col-lg-4">
                <img class="rounded-circle"
                     src="http://i.telegraph.co.uk/multimedia/archive/01773/spider-man-outfit_1773895i.jpg"
                     alt="Generic placeholder image" width="140" height="140">
                <h2>Człowiek pająk</h2>
                <p>Świetna wyszukiwarka! Dzięki ShopAll znalazłem dziewczynę!</p>
            </div>
        </div>

        <hr class="featurette-divider">

        <div class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading">${requestScope['t.mainMenuMostPopular']}<br/> <span
                        class="text-muted" style="word-break: keep-all"><a class="dashboard-link" target="_blank" href="${image5.getAllegroLink()}">${image4.getName()}</a></span></h2>
                <p class="lead">${requestScope['t.mainMenuMostPopularInfo']}
                ${image4.getName()}!</p>
            </div>
            <div class="col-md-5">
                <a target="_blank" href="${image4.getAllegroLink()}"><img class="featurette-image img-fluid mx-auto" alt="500x500"
                     style="width: 500px; height: 500px; object-fit: cover;"
                     src=${image4.getUrl()}></a>
            </div>
        </div>

        <hr class="featurette-divider">

        <div class="row featurette">
            <div class="col-md-7 order-md-2">
                <h2 class="featurette-heading">${requestScope['t.mainMenuCategoryNew']}<br/><span
                        class="text-muted" style="word-break: keep-all"><a class="dashboard-link" target="_blank" href="${image5.getAllegroLink()}">${image5.getName()}</a></span></h2>
                <p class="lead">${requestScope['t.mainMenuCategoryPrompt']} ${image5.getName()}!</p>
            </div>
            <div class="col-md-5 order-md-1">
                <a target="_blank" href="${image5.getAllegroLink()}"><img class="featurette-image img-fluid mx-auto" alt="500x500"
                     style="width: 500px; height: 500px; object-fit: cover;"
                     src=${image5.getUrl()}></a>
            </div>
        </div>

        <hr class="featurette-divider">

    </div>

    <footer class="container">
        <p class="float-right"><a href="#">${requestScope['t.common.BackToTop']}</a></p>
        <p>© 2017 Company, Inc. · <a href="#">${requestScope['t.common.Privacy']}</a> · <a href="#">${requestScope['t.common.Terms']}</a></p>
    </footer>

</main>


<svg xmlns="http://www.w3.org/2000/svg" width="500" height="500" viewBox="0 0 500 500" preserveAspectRatio="none"
     style="display: none; visibility: hidden; position: absolute; top: -100%; left: -100%;">
    <text x="0" y="25" style="font-weight:bold;font-size:25pt;font-family:Arial, Helvetica, Open Sans, sans-serif">
        500x500
    </text>
</svg>
</body>
</html>