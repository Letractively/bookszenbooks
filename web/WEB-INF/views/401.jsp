<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp" />

<h1>${lexicon.unauthorized}</h1>
<p>${lexicon.unauthorizedDesc}</p>

<c:if test="${authUser == null}">
    <c:import url="loginForm.jsp" />
</c:if>

<c:import url="footer.jsp" />