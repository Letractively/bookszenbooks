<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>${lexicon.search}</h3>
<div class="simpleSearch">
    <form action="bookSearch" method="get">
        <input type="text" name="keywords" id="searchKeywords" />
        <input type="submit" value="${lexicon.go}" class="button" /><br />
        <div class="advancedSearch">
            <a href="bookSearch?action=advancedSearch">${lexicon.advancedSearch}</a>
        </div>
    </form>
</div>
<h3>${lexicon.subjects}</h3>
<ul>
    <c:forEach var="subject" items="${subjects}">
        <li><a href="bookSearch?subject=${subject.subjectId}">${subject.i18nText}</a></li>
    </c:forEach>
    
</ul>
