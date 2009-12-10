<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="${language}">
    <head>
        <title>${config.siteTitle} .::. ${pageTitle}</title>
        <base href="http://localhost:8081/Books_Zen_Books/" />
        <meta http-equiv="Content-Type" content="text/html; charset=${config.charset}" />
        <link rel="stylesheet" type="text/css" href="${config.baseUrl}assets/styles/global.css" />
    </head>
    <body>
        <div id="pageWrap">
            <div id="topStrip">
                <p>
                <c:choose>
                    <c:when test="${authUser != null}">
                        <a href="accountLogin?action=logout">${lexicon.logOut}</a>
                    </c:when>
                    <c:otherwise>
                        <a href="accountLogin">${lexicon.logIn}</a> &middot;
                        <a href="registerAccount">${lexicon.registerAccount}</a>
                    </c:otherwise>
                </c:choose>
                </p>
            </div>
            <div id="header">
                <a href="${config.baseUrl}"><img src="${config.baseUrl}assets/images/geek.png" alt="${config.siteTitle}" title="${config.siteTitle}" width="107" height="141" />
                    <img src="${config.baseUrl}assets/images/logo.png" alt="${config.siteTitle}" title="${config.siteTitle}" width="580" height="141" /></a>
            </div>
            <div id="pageColumns">
                <div id="columnWrap">
                    <div id="columnContent">
                        
                
        