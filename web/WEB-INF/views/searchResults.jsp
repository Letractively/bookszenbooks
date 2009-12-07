<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.jsp" />

<h1>${lexicon.searchResults}</h1>

<c:choose>
    <c:when test="${empty listings}">
        <div class="noSearchResults">
            <p>${lexicon.noResults}</p>
        </div>
    </c:when>
    <c:otherwise>
        <c:forEach var="listing" items="${listings}">
            <div class="searchResult">
                <strong><a href="displayListing?listId=${listing.listId}">${listing.book.title}</a></strong>
                    ${lexicon.by}
                    <c:forTokens var="author" items="${listing.book.author}" delims="|" varStatus="status">
                        <a href="bookSearch?author=${author}">${author}</a><c:if test="${status.last != true}">, </c:if>
                    </c:forTokens><br />
                    ${lexicon.price}: ${listing.price}<br />
                    ${lexicon.publishDate}: ${listing.book.publishDate}<br />
                    ${lexicon.listedBy} <a href="displayUser?userId=${listing.userId}">${listing.user.email}</a> ${lexicon.on} ${listing.listDate}
            </div>
        </c:forEach>
    </c:otherwise>
</c:choose>


<c:import url="/includes/footer.jsp" />