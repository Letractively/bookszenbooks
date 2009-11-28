<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.jsp" />

<c:choose>
    <c:when test="${user != null}">
        <a href="accountLogin?action=logout">${lexicon.logOut}</a>
    </c:when>
    <c:otherwise>
        [<a href="accountLogin">${lexicon.logIn}</a>] &middot;
        [<a href="registerAccount">${lexicon.register}</a>]
    </c:otherwise>
</c:choose>

<div>
    Email: ${user.email}<br />
    Password (Hashed): ${user.password}<br />
    First Name: ${user.firstName}<br />
    Last Name: ${user.lastName}
</div>

<c:import url="/includes/footer.jsp" />