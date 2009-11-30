<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.jsp" />

<c:choose>
    <c:when test="${listing == null}">
        <div class="error">
            <p>${lexicon.listingIDInvalid}</p>
        </div>
    </c:when>
    <c:otherwise>
        <h1>${listing.book.title}
            <em id="authorBox">${lexicon.by}
                <c:forTokens var="author" items="${listing.book.author}" delims="|" varStatus="status">
                    <a href="bookSearch?author=${author}">${author}</a>
                    <c:if test="${status.last != true}">, </c:if>
                </c:forTokens>
            </em>
        </h1>
        <c:if test="${not listing.active}">
            <div class="error">
                <p>${lexicon.listingInactive}</p>
            </div>
        </c:if>
        <div id="priceBox">
            <span class="priceLabel">${lexicon.price}:</span> <span class="buyPrice">${listing.price}</span>
        </div>
        <c:if test="${not empty otherFormats}">
            <div id="otherFormatsBox">
                <table>
                    <tr>
                        <th>${lexicon.format}</th>
                        <th>${lexicon.numberAvailable}</th>
                        <th>${lexicon.averagePrice}</th>
                    </tr>
                    <c:forEach var="format" items="${otherFormats}">
                        <tr>
                            <td>${format[0]}</td>
                            <td>${format[1]}</td>
                            <td>${format[2]}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:if>

        <div id="listingInfoBox">
            <h2>${lexicon.bookDetails}</h2>
            <ul>
                <li><b>${lexicon.isbn}:</b> ${listing.book.isbn}</li>
                <li><b>${lexicon.publisher}:</b> ${listing.book.publisher}</li>
                <li><b>${lexicon.publishDate}:</b> ${listing.book.publishDate}</li>
                <li><b>${lexicon.edition}:</b> ${listing.book.edition}</li>
                <li><b>${lexicon.pages}:</b> ${listing.book.pages}</li>
                <li><b>${lexicon.language}:</b> ${listing.book.language}</li>
                <li><b>${lexicon.format}:</b> ${listing.book.format}</li>
                <li><b>${lexicon.condition}:</b> ${listing.condition}</li>
            </ul>
        </div>
        <div id="listingDetails">
            <h2>${lexicon.listingDetails}</h2>
            <ul>
                <c:choose>
                    <c:when test="${listing.comment != null}">
                        <li><b>${lexicon.sellerComments}:</b> ${listing.comment}</li>
                    </c:when>
                    <c:otherwise>
                        <li><b>${lexicon.sellerComments}:</b> ${lexicon.noComment}</li>
                    </c:otherwise>
                </c:choose>
                <li><b>${lexicon.listDate}:</b> ${listing.listDate}</li>
                <li><a href="displayUser?userId=${listing.userId}">${lexicon.sellerProfile}</a></li>
            </ul>
        </div>
    </c:otherwise>
</c:choose>




<c:import url="/includes/footer.jsp" />