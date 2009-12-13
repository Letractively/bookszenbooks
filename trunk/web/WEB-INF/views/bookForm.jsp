<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty formErrors.isbn}"><p class="error">${formErrors.isbn}</p></c:if>
<label for="isbn">${lexicon.isbn}:</label><input type="text" name="isbn" id="isbn" value="${param.isbn}" /><br />
<c:if test="${not empty formErrors.title}"><p class="error">${formErrors.title}</p></c:if>
<label for="title">${lexicon.title}:</label><input type="text" name="title" id="title" value="${param.title}" /><br />
<c:if test="${formErrors.author != null}"><p class="error">${formErrors.author}</p></c:if>
<label for="author">${lexicon.author}:</label><textarea name="author" id="author" cols="20" rows="3">${param.author}</textarea><br />
<c:if test="${formErrors.edition != null}"><p class="error">${formErrors.edition}</p></c:if>
<label for="edition">${lexicon.edition}:</label><input type="text" name="edition" id="edition" value="${param.edition}" size="3" /><br />
<c:if test="${formErrors.subject != null}"><p class="error">${formErrors.subject}</p></c:if>
<label for="subject">${lexicon.subject}:</label>
<select name="subject" id="subject">
    <c:forEach var="subject" items="${subjects}">
        <c:choose>
            <c:when test="${subject.subjectId == param.subject}">
                <option value="${subject.subjectId}" selected="selected">${subject.i18nText}</option>
            </c:when>
            <c:otherwise>
                <option value="${subject.subjectId}">${subject.i18nText}</option>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</select><br />
<c:if test="${formErrors.format != null}"><p class="error">${formErrors.format}</p></c:if>
<label>${lexicon.format}:${param.format}</label>
<input type="radio" name="format" id="paperback" value="paperback" <c:if test="${param.format == 'paperback'}">checked="checked"</c:if> /><label for="paperback" class="noFloat">${lexicon.paperback}</label>
<input type="radio" name="format" id="hardcover" value="hardcover" <c:if test="${param.format == 'hardcover'}">checked="checked"</c:if> /><label for="hardcover" class="noFloat">${lexicon.hardcover}</label><br />
<c:if test="${formErrors.language != null}"><p class="error">${formErrors.language}</p></c:if>
<label for="language">${lexicon.language}</label>
<select name="language" id="language">
    <c:forEach var="language" items="${languages}">
        <c:choose>
            <c:when test="${language.key == 'en' and empty param.language or language.key == param.language}">
                <option value="${language.key}" selected="selected">${language.value}</option>
            </c:when>
            <c:otherwise>
                <option value="${language.key}">${language.value}</option>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</select><br />
<c:if test="${formErrors.pages != null}"><p class="error">${formErrors.pages}</p></c:if>
<label for="pages">${lexicon.pages}:</label><input type="text" name="pages" id="pages" value="${param.pages}" size="3" /><br />
<c:if test="${formErrors.publisher != null}"><p class="error">${formErrors.publisher}</p></c:if>
<label for="publisher">${lexicon.publisher}:</label><input type="text" name="publisher" id="publisher" value="${param.publisher}" /><br />
<c:if test="${formErrors.publishDate != null}"><p class="error">${formErrors.publishDate}</p></c:if>
<label for="publishDate">${lexicon.publishDate}:</label><input type="text" name="publishDate" id="publishDate" value="${param.publishDate}" /><br />
