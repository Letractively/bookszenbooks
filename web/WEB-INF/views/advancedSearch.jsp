<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp" />

<h1>${lexicon.advancedSearch}</h1>

<form action="bookSearch" method="get">
    <fieldset>
        <table>
            <tr>
                <td width="50%">
                    <label for="author">${lexicon.author}:</label><input type="text" name="author" id="author" /><br />
                    <label for="title">${lexicon.title}:</label><input type="text" name="title" id="title" /><br />
                    <label for="isbn">${lexicon.isbn}:</label><input type="text" name="isbn" id="isbn" /><br />
                    <label for="publisher">${lexicon.publisher}:</label><input type="text" name="publisher" id="publisher" /><br />
                    <label for="subject">${lexicon.subject}:</label>
                        <select name="subject" id="subject">
                            <c:forEach var="subject" items="${subjects}">
                                <option value="${subject.subjectId}">${subject.i18nText}</option>
                            </c:forEach>
                        </select><br />
                </td>
                <td width="50%">
                    <label for="condition">${lexicon.condition}:</label>
                        <select name="condition" id="condition">
                            <option value="">${lexicon.anyCondition}</option>
                            <c:forEach var="condition" items="${conditions}">
                                <option value="${condition.key}">${condition.value}</option>
                            </c:forEach>
                        </select><br />
                    <label for="language">${lexicon.language}:</label>
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
                        </select><br />
                    <label for="sortBy">${lexicon.sortBy}:</label>
                        <select name="sortBy" id="sortBy">
                            <c:forEach var="sortBy" items="${sortFields}">
                                <option value="${sortBy.key}">${sortBy.value}</option>
                            </c:forEach>
                        </select><br />
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="${lexicon.search}" class="button submit" />
                </td>
            </tr>
        </table>
    </fieldset>
</form>

<c:import url="footer.jsp" />