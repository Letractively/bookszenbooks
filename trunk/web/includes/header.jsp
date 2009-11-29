<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="${request.language}">
    <head>
        <title>${config.siteTitle} .::. ${pageTitle}</title>
        <base href="http://localhost:8081/Books_Zen_Books/" />
        <meta http-equiv="Content-Type" content="text/html; charset=${config.charset}" />
        <link rel="stylesheet" type="text/css" href="${config.baseUrl}global.css" />
    </head>
    <body>
        <div id="loginBar">
            <c:choose>
                <c:when test="${user != null}">
                    <a href="accountLogin?action=logout">${lexicon.logOut}</a>
                </c:when>
                <c:otherwise>
                    [<a href="accountLogin">${lexicon.logIn}</a>] &middot;
                    [<a href="registerAccount">${lexicon.registerAccount}</a>]
                </c:otherwise>
            </c:choose>
        </div>
        <div id="simpleSearch">
            <c:import url="/includes/simpleSearch.jsp" />
        </div>