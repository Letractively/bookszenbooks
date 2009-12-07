<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp" />

<h1>${pageTitle}</h1>
<span class="label">${lexicon.memberSince}:</span> ${user.joinDate}<br />
<span class="label">${lexicon.totalListings}:</span> ${stats.totalListings}

<h2>${lexicon.recentListings}</h2>
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
                    ${lexicon.listedOn}: ${listing.listDate}
            </div>
        </c:forEach>
    </c:otherwise>
</c:choose>

<c:import url="footer.jsp" />