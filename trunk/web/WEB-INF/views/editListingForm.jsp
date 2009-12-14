<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp" />

<p>${lexicon.editListingDesc}</p>

<form action="editListing" method="post">
    <fieldset>
        <input type="hidden" name="action" value="save" />
        <input type="hidden" name="listId" value="${listing.listId}" />
        <label>${lexicon.title}:</label><span class="label">${listing.book.title}</span><br />
        <label>${lexicon.author}:</label><span class="label">${listing.book.author}</span><br />
        <label>${lexicon.isbn}:</label><span class="label">${listing.book.isbn}</span><br />
        <c:if test="${not empty formErrors.price}"><p class="error">${formErrors.price}</p></c:if>
        <label for="price">${lexicon.price}:</label><input type="text" name="price" id="price" value="${listing.price}" size="5" /><br />
        <c:if test="${not empty formErrors.condition}"><p class="error">${formErrors.condition}</p></c:if>
        <label for="condition">${lexicon.condition}:</label>
        <select name="condition" id="condition">
            <c:forEach var="condition" items="${conditions}">
                <c:choose>
                    <c:when test="${listing.condition == condition.key}">
                        <option value="${condition.key}" selected="selected">${condition.value}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${condition.key}">${condition.value}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select><br />
        <label for="comment">${lexicon.comments}:</label><textarea name="comment" id="comment" rows="3" cols="20">${listing.comment}</textarea><br />
        <label for="active">${lexicon.active}:</label>
        <input type="checkbox" name="active" id="active" value="true" <c:if test="${listing.active}">checked="checked"</c:if> /><br />
        <input type="submit" value="${lexicon.continue}" class="button submit" />
    </fieldset>
</form>

<c:import url="footer.jsp" />