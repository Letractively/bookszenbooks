<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp" />

<p>${lexicon.myListingsDesc}</p>

<c:choose>
    <c:when test="${empty listings}">
        <div class="noSearchResults">
            <p>${lexicon.noResults}</p>
        </div>
    </c:when>
    <c:otherwise>
        <ul class="searchResults">
        <c:forEach var="listing" items="${listings}">
            <li>
                <div class="listingOptions">
                    <a href="editListing?listId=${listing.listId}">${lexicon.editListing}</a>
                </div>
                <strong><a href="displayListing?listId=${listing.listId}">${listing.book.title}</a></strong>
                    ${lexicon.by}
                    <c:forTokens var="author" items="${listing.book.author}" delims="|" varStatus="status">
                        <a href="bookSearch?author=${author}">${author}</a><c:if test="${status.last != true}">, </c:if>
                    </c:forTokens><br />
                    ${lexicon.price}: ${listing.price}<br />
                    ${lexicon.listedOn}: ${listing.listDate}<br />
                    <c:if test="${not listing.active}">
                        <em>${lexicon.listingInactive}</em>
                    </c:if>
            </li>
        </c:forEach>
        </ul>
    </c:otherwise>
</c:choose>


<c:import url="footer.jsp" />