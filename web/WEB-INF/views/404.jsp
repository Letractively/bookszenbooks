<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp" />

<h1>${pageTitle}</h1>

<c:choose>
    <c:when test="${not empty customError}">
        <p>${customError}</p>
    </c:when>
    <c:otherwise>
        <p>${lexicon.fileNotFoundDesc}</p>
    </c:otherwise>
</c:choose>
        
<c:import url="footer.jsp" />