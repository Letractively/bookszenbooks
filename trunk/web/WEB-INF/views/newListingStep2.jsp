<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp" />

<c:choose>
    <c:when test="${empty book}">
        <p>${lexicon.newListingNoMatch}</p>
        <form action="addListing" method="post">
            <fieldset>
                <input type="hidden" name="step" value="3" />
                <input type="hidden" name="newBook" value="true" />
                <c:import url="bookForm.jsp" />
                <input type="submit" value="${lexicon.continue}" class="button submit" />
            </fieldset>
        </form>
    </c:when>
    <c:otherwise>
        <p>${lexicon.newListingMatch}</p>
        <form action="addListing" method="post">
            <fieldset>
                <input type="hidden" name="step" value="3" />
                <input type="hidden" name="newBook" value="false" />
                <label>${lexicon.title}:</label><span class="label">${book.title}</span><br />
                <label>${lexicon.author}:</label><span class="label">${book.author}</span><br />
                <label>${lexicon.edition}:</label><span class="label">${book.edition}</span><br />
                <label>${lexicon.subject}:</label><span class="label">${book.subjectId}</span><br />
                <label>${lexicon.format}:</label><span class="label">${book.format}</span><br />
                <label>${lexicon.language}:</label><span class="label">${book.language}</span><br />
                <label>${lexicon.pages}:</label><span class="label">${book.pages}</span><br />
                <label>${lexicon.publisher}:</label><span class="label">${book.publisher}</span><br />
                <label>${lexicon.publishDate}:</label><span class="label">${book.publishDate}</span><br />
                <input type="submit" value="${lexicon.continue}" class="button submit" />
            </fieldset>
        </form>
    </c:otherwise>
</c:choose>

<c:import url="footer.jsp" />