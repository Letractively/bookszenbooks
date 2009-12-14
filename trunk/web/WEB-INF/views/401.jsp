<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp" />

<h1>${pageTitle}</h1>
<c:choose>
    <c:when test="${not empty customError}">
        <p>${customError}</p>
    </c:when>
    <c:otherwise>
        <p>${lexicon.unauthorizedDesc}</p>
    </c:otherwise>
</c:choose>

<c:if test="${authUser == null}">
    <c:import url="loginForm.jsp" />
</c:if>

<c:import url="footer.jsp" />