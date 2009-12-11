<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp" />

<form action="accountLogin" method="post">
    <fieldset>
        <input type="hidden" name="action" value="login" />
        <c:if test="${formError != null}">
            <p class="error">${formError}</p>
        </c:if>
        <label for="email">${lexicon.email}:</label><input type="text" name="email" id="email" /><br />
        <label for="password">${lexicon.password}:</label><input type="password" name="password" id="password" /><br />
        <input type="submit" value="${lexicon.logIn}" class="button submit" />
    </fieldset>
</form>

<c:import url="footer.jsp" />