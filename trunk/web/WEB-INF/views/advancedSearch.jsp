<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp" />

<h1>${lexicon.advancedSearch}</h1>

<form action="bookSearch" method="get">
    <table>
        <tr>
            <td width="50%">
                <p><label for="author">${lexicon.author}:</label><input type="text" name="author" id="author" /></p>
                <p><label for="title">${lexicon.title}:</label><input type="text" name="title" id="title" /></p>
                <p><label for="isbn">${lexicon.isbn}:</label><input type="text" name="isbn" id="isbn" /></p>
                <p><label for="publisher">${lexicon.publisher}:</label><input type="text" name="publisher" id="publisher" /></p>
                <p><label for="subject">${lexicon.subject}:</label>
                    <select name="subject">
                        <c:forEach var="subject" items="${subjects}">
                            <option value="${subject.subjectId}">${subject.i18nText}</option>
                        </c:forEach>
                    </select>
                </p>
            </td>
            <td width="50%">
                <p><label for="condition">${lexicon.condition}:</label>
                    <select name="condition" id="condition">
                        <option value="">${lexicon.anyCondition}</option>
                        <c:forEach var="condition" items="${conditions}">
                            <option value="${condition.key}">${condition.value}</option>
                        </c:forEach>
                    </select></p>
                <p><label for="language">${lexicon.language}:</label>
                    <select name="language" id="language">
                        <c:forEach var="language" items="${languages}">
                            <c:choose>
                                <c:when test="${language.key == 'en'}">
                                    <option value="${language.key}" selected="selected">${language.value}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${language.key}">${language.value}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select></p>
                <p><label for="sortBy">${lexicon.sortBy}:</label>
                    <select name="sortBy" id="sortBy">
                        <c:forEach var="sortBy" items="${sortFields}">
                            <option value="${sortBy.key}">${sortBy.value}</option>
                        </c:forEach>
                    </select></p>
            </td>
        </tr>
        <tr>
            <td>
                <p class="submit"><input type="submit" value="${lexicon.search}" /></p>
            </td>
        </tr>
    </table>

</form>

<c:import url="footer.jsp" />