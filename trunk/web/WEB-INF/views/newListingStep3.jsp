<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp" />

${userListing.book.newObject}<br />
${userListing.active}

<p>${lexicon.enterListingDesc}</p>

<form action="addListing" method="post">
    <fieldset>
        <input type="hidden" name="step" value="4" />
        <label>${lexicon.title}:</label><span class="label">${userListing.book.title}</span><br />
        <label>${lexicon.author}:</label><span class="label">${userListing.book.author}</span><br />
        <label>${lexicon.isbn}:</label><span class="label">${userListing.book.isbn}</span><br />
        <c:if test="${formErrors.price != null}"><p class="error">${formErrors.price}</p></c:if>
        <label for="price">${lexicon.price}:</label><input type="text" name="price" id="price" value="${param.price}" size="5" /><br />
        <c:if test="${formErrors.condition != null}"><p class="error">${formErrors.condition}</p></c:if>
        <label for="condition">${lexicon.condition}:</label>
        <select name="condition" id="condition">
            <c:forEach var="condition" items="${conditions}">
                <option value="${condition.key}">${condition.value}</option>
            </c:forEach>
        </select><br />
        <label for="comment">${lexicon.comments}:</label><textarea name="comment" id="comment" rows="3" cols="20">${param.comment}</textarea><br />
        <input type="submit" value="${lexicon.continue}" class="button submit" />
    </fieldset>
</form>

<c:import url="footer.jsp" />