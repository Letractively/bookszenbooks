<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<label for="isbn">${lexicon.isbn}:</label><input type="text" name="isbn" id="isbn" value="${param.isbn}" /><br />
<label for="title">${lexicon.title}:</label><input type="text" name="title" id="title" value="${param.title}" /><br />
<label for="author">${lexicon.author}:</label><textarea name="author" id="author" cols="20" rows="3">${param.author}</textarea><br />
<label for="edition">${lexicon.edition}:</label><input type="text" name="edition" id="edition" value="${param.edition}" size="3" /><br />
<label for="subject">${lexicon.subject}:</label>
<select name="subject" id="subject">
    <c:forEach var="subject" items="${subjects}">
        <option value="${subject.subjectId}">${subject.i18nText}</option>
    </c:forEach>
</select><br />
<label>${lexicon.format}:</label>
<input type="radio" name="format" id="paperback" /><label for="paperback" class="noFloat">${lexicon.paperback}</label>
<input type="radio" name="format" id="hardcover" /><label for="hardcover" class="noFloat">${lexicon.hardcover}</label><br />
<label for="language">${lexicon.language}</label>
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
<label for="pages">${lexicon.pages}:</label><input type="text" name="pages" id="pages" value="${param.pages}" size="3" /><br />
<label for="publisher">${lexicon.publisher}:</label><input type="text" name="publisher" id="publisher" value="${param.publisher}" /><br />
<label for="publishDate">${lexicon.publishDate}:</label><input type="text" name="publishDate" id="publishDate" value="${param.publishDate}" /><br />
