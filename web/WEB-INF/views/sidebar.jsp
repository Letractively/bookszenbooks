<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>${lexicon.search}</h3>
<div class="simpleSearch">
    <form action="bookSearch" method="get">
        <input type="text" name="keywords" id="searchKeywords" />
        <input type="submit" value="${lexicon.go}" class="button" /><br />
        <a href="bookSearch?action=advancedSearch" class="advancedSearchLink">${lexicon.advancedSearch}</a>
    </form>
</div>
<h3>Categories</h3>