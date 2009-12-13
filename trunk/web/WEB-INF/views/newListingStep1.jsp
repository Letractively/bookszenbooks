<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp" />

<p>${lexicon.beginAddListing}</p>

<c:if test="${formErrors.page != null}"><p class="error">${formErrors.page}</p></c:if>

<form action="addListing" method="post">
    <fieldset>
        <input type="hidden" name="step" value="2" />
        <c:if test="${formErrors.isbn != null}"><p class="error">${formErrors.isbn}</p></c:if>
        <label for="isbn">${lexicon.isbn}:</label><input type="text" name="isbn" id="isbn" size="10" /><br />
        <input type="submit" value="${lexicon.continue}" class="button submit" />
    </fieldset>

</form>

<c:import url="footer.jsp" />