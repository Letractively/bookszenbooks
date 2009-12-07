<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp" />

<p>${formError}</p>

<form action="accountLogin" method="post">
    <input type="hidden" name="action" value="login" />
    <p><label for="email">${lexicon.email}:</label><input type="text" name="email" id="email" /></p>
    <p><label for="password">${lexicon.password}:</label><input type="password" name="password" id="password" /></p>
    <p class="submit"><input type="submit" value="${lexicon.logIn}" /></p>
</form>

<c:import url="footer.jsp" />