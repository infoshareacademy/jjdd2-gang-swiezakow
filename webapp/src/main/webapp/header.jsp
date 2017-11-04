<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <a class="navbar-brand" href="main">ShopAll</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
            aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item <c:if test="${'1' == categoryNumber}">active</c:if>">
                <a class="nav-link" href="SearchByQuestions">${requestScope['t.common.seriesOfQuestions']}</a>
            </li>
            <li class="nav-item <c:if test="${'2' == categoryNumber}">active</c:if>">
                <a class="nav-link" href="searchCategoryCommand">${requestScope['t.header.selectProduct']}</a>
            </li>
            <li class="nav-item <c:if test="${'3' == categoryNumber}">active</c:if>">
                <a class="nav-link" href="categoryPickerCommand">${requestScope['t.header.allegroCatalog']}</a>
            </li>
            <li class="nav-item <c:if test="${'4' == categoryNumber}">active</c:if>">
                <a class="nav-link" href="searchQueryCommand">${requestScope['t.header.allegroAssistant']}</a>
            </li>
        </ul>
        <div class="nav-item profile-section dropdown">
                    <button class="btn btn-link dropdown-toggle" type="button" href="#" id="langDropdown"
                            data-toggle="dropdown" aria-haspopup="true"
                            aria-expanded="false">
                            <c:choose>
                                <c:when test="${empty Locale}">
                                   <span class="flag-icon flag-icon-pl"></span>Polski
                                </c:when>
                                <c:otherwise>
                                   <span class="flag-icon flag-icon-us"></span>English
                                </c:otherwise>
                            </c:choose>
                    </button>
                    <div class="dropdown-menu" aria-labelledby="langDropdown">
                       <a class="dropdown-item" href="?locale="> <span class="flag-icon flag-icon-pl"></span>Polski</a>
                       <a class="dropdown-item" href="?locale=en"> <span class="flag-icon flag-icon-us"></span>English</a>
                     </div>
                </div>
        <div class="nav-item profile-section dropdown">
            <button class="btn btn-link dropdown-toggle" type="button" href="#" id="userDropdown"
                    data-toggle="dropdown" aria-haspopup="true"
                    aria-expanded="false">
                <img style="border-radius: 50%" class="profile-avatar" src="${UserUrl}">
                ${UserName}
            </button>
            <div class="dropdown-menu" aria-labelledby="userDropdown">
                <c:choose>
                    <c:when test="${isFbUser}">
                        <a class="dropdown-item" href="login?logout=1">${requestScope['t.header.logout']}</a>
                    </c:when>
                    <c:otherwise>
                        <div class="g-signin2" style="display: none;" data-onsuccess="onSignIn"></div>
                        <a class="dropdown-item" href="#" onclick="signOut();">${requestScope['t.header.logout']}</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</nav>