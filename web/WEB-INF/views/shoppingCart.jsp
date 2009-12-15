<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp" />

<p>${lexicon.cartDesc}</p>

<div class="message">
    <p>${message}</p>
</div>

<c:choose>
    <c:when test="${empty listings}">
        ${lexicon.cartEmpty}
    </c:when>
    <c:otherwise>
        <ul class="searchResults">
        <c:forEach var="listing" items="${listings}">
            <li>
                <strong><a href="displayListing?listId=${listing.listId}">${listing.book.title}</a></strong>
                    ${lexicon.by}
                    <c:forTokens var="author" items="${listing.book.author}" delims="|" varStatus="status">
                        <a href="bookSearch?author=${author}">${author}</a><c:if test="${not status.last}">, </c:if>
                    </c:forTokens><br />
                    ${lexicon.edition}: ${listing.book.edition}<br />
                    ${lexicon.price}: ${listing.price}<br />
                    ${lexicon.listedBy} <a href="displayUser?userId=${listing.userId}">${listing.user.email}</a> ${lexicon.on} ${listing.listDate}<br />
                    <c:if test="${not listing.active}">
                        <em>${lexicon.listingInactive}</em>
                    </c:if>
            </li>
        </c:forEach>
        </ul>
    </c:otherwise>
</c:choose>

<c:import url="footer.jsp" />