<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp" />

<c:choose>
    <c:when test="${empty book}">
        <p>${lexicon.newListingNoMatch}</p>
        <form action="addListing" method="post">
            <fieldset>
                <input type="hidden" name="step" value="3" />
                <c:import url="bookForm.jsp" />
                <input type="submit" value="${lexicon.continue}" class="button submit" />
            </fieldset>
        </form>
    </c:when>
    <c:otherwise>
        BOOK IS NOT NULL!
    </c:otherwise>
</c:choose>

<c:import url="footer.jsp" />