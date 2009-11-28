<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.jsp" />

<p>${lexicon.confirmDesc}</p>

<form action="registerAccount" method="post">
    <input type="hidden" name="action" value="confirm" />
    <label for="email">${lexicon.email}:</label><input type="text" name="email" id="email" value="${param.email}" />
    <c:if test="${codeInvalid == true}"><p class="error">${lexicon.codeInvalid}</p></c:if>
    <p><label for="confirmCode">${lexicon.confirmCode}:</label><input type="text" name="confirmCode" id="confirmCode" /></p>
    <p class="submit"><input type="submit" value="${lexicon.checkCode}" /></p>
</form>

<c:import url="/includes/footer.jsp" />